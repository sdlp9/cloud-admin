layui.use(['form', 'table'], function(){
    var form = layui.form,
        table = layui.table;
    table.render({
        elem: '#user-table',
        url: '/user/list',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'userId', title: 'ID', sort: true},
            {field:'username',title: '用户名'},
            {field:'email', title: '邮箱', sort: true},
            {field:'mobile', title: '电话'},
            {field:'status', title: '状态',templet: function(d){
                    if(d.status === 1){
                        return "正常"
                    }else{
                        return "已删除"
                    }
                }
            },
            {field:'createTime', title: '创建时间', sort: true}
        ]],
        id: 'userTable',
        page: true,
        limits: [20,30,50,100],
        limit: 10 //默认采用10
    });
});
var vm = new Vue({
    el:'#user-list',
    data:{
        q:{
            username: null
        },
        showForm: false,
        roleList:{},
        user: {
            roleIdList:[]
        }
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('userTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    username: vm.q.username
                }
            });
        },
        fn_add : function () {
            // window.location.href = "/user/goAdd";
            vm.user = {roleIdList:[]};
            //获取角色信息
            vm.getRoleList();
            vm.showForm = true;
        },
        fn_update : function () {
            var checkStatus = layui.table.checkStatus('userTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要修改的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行修改");
                return false;
            }
            vm.getUser(data[0].userId);
            //获取角色信息
            this.getRoleList();
            vm.showForm = true;
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "/user/save" : "/user/update";
            if(vm.validator()){
                return ;
            }
            vm.user.roleIdList = [];
            layui.$("input:checkbox[name='roleIdList']:checked").each(function() {
                vm.user.roleIdList.push(layui.$(this).val());
            });
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function(r){
                    if(r.code === 0){
                        layui.layer.msg('操作成功', {icon: 1});
                        vm.query();
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        fn_delete: function(){
            var checkStatus = layui.table.checkStatus('userTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择需要删除数据");
                return false;
            }
            layer.confirm('是否确定删除选择的数据？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                var ids = [];
                layui.$.each(data, function(index, item) {
                    ids.push(item.userId);
                });
                layui.$.ajax({
                    type: "POST",
                    url: "/user/deleteByIds",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code === 0){
                            layui.layer.msg('操作成功', {icon: 1});
                            vm.query();
                        }else{
                            layui.layer.msg(r.msg);
                        }
                    }
                });
            });
        },
        getUser: function(userId){
            layui.$.get("/user/info/"+userId, function(r){
                vm.user = r.user;
                vm.user.password = null;
                vm.user.salt = null;
            });
        },
        getRoleList: function(){
            layui.$.get("/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        validator: function () {
            if(vm.isBlank(vm.user.username)){
                layui.layer.msg("用户名不能为空");
                return true;
            }

            if(vm.user.userId == null && vm.isBlank(vm.user.password)){
                layui.layer.msg("密码不能为空");
                return true;
            }
            if(vm.isBlank(vm.user.mobile)){
                layui.layer.msg("手机号不能为空");
                return true;
            }
            var mobile = /^1[3|4|5|8|7][0-9]\d{4,8}$/;
            if(!mobile.test(vm.user.mobile)){
                ayui.layer.msg("手机号码格式不正确");
                return true;
            }
            if(vm.isBlank(vm.user.email)){
                layui.layer.msg("邮箱不能为空");
                return true;
            }
            var reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
            if(!reg.test(vm.user.email)){
                layui.layer.msg("邮箱格式不正确");
                return true;
            }
        }
    }
});
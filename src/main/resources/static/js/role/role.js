layui.use(['form', 'upload','layedit','layer','table','laydate'], function(){
    var form = layui.form,
        upload = layui.upload,
        table = layui.table,
        laydate  = layui.laydate,
        layedit = layui.layedit,
        layer = layui.layer,
        $ = layui.$;
    table.render({
        elem: '#role-table',
        url: '/role/list',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'roleId', title: 'ID', sort: true},
            {field:'roleName',title: '角色名'},
            {field:'remark', title: '备注', sort: true},
            {field:'createTime', title: '创建时间', sort: true}
        ]],
        id: 'roleTable',
        page: true,
        limits: [20,30,50,100],
        limit: 10 //默认采用10
    });
});
//菜单树
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl",
            icon:null
        }
    },
    check:{
        enable:true,
        nocheckInherit:true
    }
};
var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            rolename: null
        },
        showForm: false,
        title: '角色列表',
        role: {}
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('roleTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    rolename: vm.q.rolename
                }
            });
        },
        fn_add : function () {
            vm.getMenuTree(null);
            vm.role = {};
            vm.showForm = true;
            vm.title = '增加角色';
        },
        fn_update : function () {
            var checkStatus = layui.table.checkStatus('roleTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要修改的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行修改");
                return false;
            }
            vm.getMenuTree(data[0].roleId);
            vm.getRole(data[0].roleId);
            vm.showForm = true;
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        saveOrUpdate: function () {
            var url = vm.role.roleId == null ? "/role/save" : "/role/update";
            //获取选择的菜单
            var nodes = menu_ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for(var i=0; i<nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;
            if(vm.validator()){
                return ;
            }
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.role),
                success: function(r){
                    if(r.code === 0){
                        layui.layer.msg('操作成功');
                        vm.query();
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        fn_delete: function(){
            var checkStatus = layui.table.checkStatus('roleTable'),
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
                    url: "/role/deleteByIds",
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
        getRole: function(roleId){
            layui.$.get("/role/info/"+roleId, function(r){
                vm.role = r.role;
                //勾选角色所拥有的菜单
                var menuIds = vm.role.menuIdList;
                for(var i=0; i<menuIds.length; i++) {
                    var node = menu_ztree.getNodeByParam("menuId", menuIds[i]);
                    menu_ztree.checkNode(node, true, false);
                }
            });
        },
        getMenuTree: function (roleId) {
            //加载菜单树
            layui.$.get("/menu/list", function(r){
                menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
                //展开所有节点
                menu_ztree.expandAll(true);

                if(roleId != null){
                    vm.getRole(roleId);
                }
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        validator: function () {
            if(vm.isBlank(vm.role.roleName)){
                layui.layer.msg("角色名称不能为空");
                return true;
            }
        }
    }
});
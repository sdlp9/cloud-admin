layui.use(['form', 'table'], function(){
    var form = layui.form,
        table = layui.table;
    table.render({
        elem: '#member-table',
        url: '/member/list',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'id', title: 'ID', sort: true},
            {field:'name', title: '姓名', sort: true},
            {field:'age', title: '年龄', sort: true},
            {field:'username',title: '用户名'},
            {field:'email', title: '邮箱', sort: true},
            {field:'phone', title: '电话'},
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
        id: 'memberTable',
        page: true,
        limits: [20,30,50,100],
        limit: 10 //默认采用10
    });
});
var vm = new Vue({
    el:'#member-html',
    data:{
        q:{
            name: null
        },
        title: '会员列表',
        showForm: false,
        member:{}
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('memberTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    name: vm.q.name
                }
            });
        },
        fn_update : function () {
            var checkStatus = layui.table.checkStatus('memberTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要查看的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行查看");
                return false;
            }
            vm.title='会员详情';
            vm.getMember(data[0].id);
            vm.showForm = true;
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        getMember: function(memberId){
            layui.$.get("/member/info/"+memberId, function(r){
                vm.member = r.member;
                vm.member.password = null;
                vm.member.salt = null;
            });
        }
    }
});
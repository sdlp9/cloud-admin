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
    var methods = {
        fn_search : function () {
            var username = $('#userName');
            //执行重载
            table.reload('roleTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    username: username.val()
                }
            });
        },
        fn_add : function () {
            window.location.href = "/role/goAdd";
        }
    };
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        methods[type] ? methods[type].call(this) : '';
    });
});
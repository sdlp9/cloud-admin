layui.use(['form', 'table','upload'], function(){
    var form = layui.form,
        table = layui.table,
        upload = layui.upload,
        $ = layui.$;
    table.render({
        elem: '#news-table',
        url: '/news/list',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'id', title: 'ID', sort: true},
            {field:'title',title: '资讯标题'},
            {field:'showFlag', title: '是否显示', sort: true,templet: function(d){
                    if(d.showFlag === 1){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },
            {field:'type', title: '资讯类型',templet: function(d){
                    if(d.type === 1){
                        return "广告"
                    }else{
                        return "新闻"
                    }
                }
            },
            {field:'createTime', title: '创建时间', sort: true}
        ]],
        id: 'newsTable',
        page: true,
        limits: [10,20,50,100],
        limit: 10 //默认采用10
    });
    form.on('select(showFlag)', function(data){
        vm.news.showFlag = data.value;
    });
    form.on('select(type)', function(data){
        vm.news.type = data.value;
    });
    //多图片上传
    upload.render({
        elem: '#test2',
        url: '/upload/',
        multiple: true,
        before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" width="100" height="100">')
            });
        }
        ,done: function(res){
            //上传完毕
        }
    });
});
var vm = new Vue({
    el:'#news-html',
    data:{
        q:{
            newsTitle: null
        },
        title: '资讯列表',
        showForm: false,
        news: {}
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('newsTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    title: vm.q.newsTitle
                }
            });
        },
        fn_add : function () {
            vm.title = '资讯增加';
            vm.news = {};
            vm.showForm = true;
        },
        fn_update : function () {
            vm.title = '资讯修改';
            var checkStatus = layui.table.checkStatus('newsTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要修改的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行修改");
                return false;
            }
            vm.getNews(data[0].id);
            vm.showForm = true;
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        saveOrUpdate: function () {
            var url = vm.news.id == null ? "/news/save" : "/news/update";
            if(vm.validator()){
                return ;
            }
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.news),
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
            var checkStatus = layui.table.checkStatus('newsTable'),
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
                    ids.push(item.id);
                });
                layui.$.ajax({
                    type: "POST",
                    url: "/news/deleteByIds",
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
        getNews: function(id){
            layui.$.get("/news/info/"+id, function(r){
                vm.news = r.news;
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        validator: function () {
            if(vm.isBlank(vm.news.title)){
                layui.layer.msg("新闻标题不能为空");
                return true;
            }
            if(vm.isBlank(vm.news.type)){
                layui.layer.msg("资讯类型不能为空");
                return true;
            }
            console.log(vm.news.showFlag);
            if(vm.isBlank(vm.news.showFlag)){
                layui.layer.msg("显示与否不能为空");
                return true;
            }
        }
    }
});
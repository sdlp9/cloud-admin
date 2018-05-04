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
        method: 'post',
        auto: false,
        data: {attach_type: '1'},//附件关联类型
        multiple: true,//是否允许多文件上传。设置 true即可开启
        accept: 'images',//指定允许上传时校验的文件类型
        acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表
        exts: 'jpg|png|gif|bmp|jpeg',
        bindAction: '#testListAction',
        before: function(obj){
            //预读本地文件示例，不支持ie8
            /*obj.preview(function(index, file, result){
                $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" width="100" height="100">');
            });*/
        },
        allDone: function(obj){ //当文件全部被提交后，才触发
            //console.log(obj.total); //得到总文件数
            //console.log(obj.successful); //请求成功的文件数
            //console.log(obj.aborted); //请求失败的文件数
        },
        done: function(res){
            var pic ={} ;
            pic.filePath = res.path;
            pic.name = res.fileName;
            pic.id = res.attachId;
            /*$('#demo2').append('<img src="'+ res.path +'" alt="'+ res.fileName +'" class="layui-upload-img" width="100" height="100">');*/
            vm.picList.push(pic);
        }
    });
    /*//多文件列表示例
    var demoListView = $('#demoList'),
        uploadListIns = upload.render({
            elem: '#testList',
            url: '/upload/',
            multiple: true,
            auto: false,
            accept: 'images',//指定允许上传时校验的文件类型
            acceptMime: 'image/!*',//规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表
            exts: 'jpg|png|gif|bmp|jpeg',
            bindAction: '#testListAction',
            choose: function(obj){
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                     demoListView.append(tr);
                     vm.picList[index] = tr;
                });
            },
            done: function(res, index, upload){
                if(res.code === 0){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    //tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            },
            error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });*/
    });
var vm = new Vue({
    el:'#news-html',
    data:{
        q:{
            newsTitle: null
        },
        title: '资讯列表',
        showForm: false,
        news: {},
        picList: []
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
            vm.picList = [];
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
            vm.picList = [];
            //获取图片附件
            vm.getAttachs(data[0].id,1);
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
            var attachId = '';
            /*for (var i = 0; i < vm.picList.length; i++) {
                attachId = attachId + vm.picList[i].attachId + ",";
            }*/
            layui.$.each(vm.picList, function(index, item) {
                attachId = attachId + item.id + ",";
            });
            vm.news.attachId = attachId;
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.news),
                success: function(r){
                    if(r.code === 0){
                        //入库成功，
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
                console.log(ids)
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
        getAttachs: function (newsId,attachType) {
            var params = {
                relId:newsId,
                attachType:attachType
            };
            layui.$.ajax({
                type: "POST",
                url: "/attachs/getAttachsList",
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function(r){
                    if(r.code === 0){
                        var list = r.list;
                        layui.$.each(list, function(index, item) {
                            var pic ={} ;
                            pic.filePath = item.filePath;
                            pic.name = item.name;
                            pic.id = item.id;
                            vm.picList.push(pic);
                        });
                        /*for(var i = 0;i<list.length;i++){
                            var pic ={} ;
                            pic.filePath = list[i].filePath;
                            pic.name = list[i].name;
                            pic.id = list[i].id;
                            vm.picList.push(pic);
                        }*/
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        delete_pic: function (id) {
            layui.$("#"+id).remove();
            /*for (var i = 0; i < vm.picList.length; i++) {
                if (vm.picList[i].attachId === id){
                    vm.picList.splice(i, 1);
                }
            }*/
            layui.$.each(vm.picList, function(index, item) {
                if (item.attachId === id){
                    vm.picList.splice(i, 1);
                }
            });
            var url = '/attachs/deleteById';
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(id),
                success: function(r){
                    if(r.code === 0){
                        layui.layer.msg('删除成功', {icon: 1});
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
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
            if(vm.isBlank(vm.news.showFlag)){
                layui.layer.msg("显示与否不能为空");
                return true;
            }
        }
    }
});
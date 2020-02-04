<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-useradmin-formlist">
        </div>
        <form class="layui-form" lay-filter="component-form-group" id="submits" onsubmit="return false">

            <div class="layui-inline">
                <label class="layui-form-label">名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" value="" placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn" data-type="reloadlstBond" id="reloadlstBond">搜索</button>
            </div>
        </form>
        <hr>
        <div class="layui-card-body">
            <table id="test" lay-filter="LAY-bondTemplate-lst-manage"></table>
            <script type="text/html" id="table-bondTemplate-lst">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bondTemplateEdit">编译代码</a>
                {{#  if(d.state == 3){ }}
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bondTemplateDownload">下载</a>
                {{#  } }}
            </script>
        </div>
    </div>
</div>

<script type="text/template" id="bondTemplateList">
    <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" lay-filter="component-form-group" id="submits" onsubmit="return false">
            <div class="layui-row layui-col-space10 layui-form-item">
                <input type="hidden" name="id" value="{{ d.id }}">
                <div class="layui-col-lg12">
                    <label class="layui-form-label">项目名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="sysName" placeholder="请输入名称" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.sysName?d.sysName:'' }}" {{# if(d.id){ }}disabled{{# } }}>
                    </div>
                </div>
<#--                <div class="layui-col-lg6">-->
<#--                    <label class="layui-form-label">包路径：</label>-->
<#--                    <div class="layui-input-block">-->
<#--                        <input type="text" name="rootPath" placeholder="请输入包路径" autocomplete="off"-->
<#--                               lay-verify="required" class="layui-input"-->
<#--                               value="{{ d.rootPath?d.rootPath:'' }}" {{# if(d.id){ }}disabled{{# } }}>-->
<#--                    </div>-->
<#--                </div>-->

<#--                <div class="layui-col-lg6">-->
<#--                    <label class="layui-form-label">控制层名：</label>-->
<#--                    <div class="layui-input-block">-->
<#--                        <input type="text" name="controlName" placeholder="请输入控制层名" autocomplete="off"-->
<#--                               class="layui-input" value="" >-->
<#--                    </div>-->
<#--                </div>-->

<#--                <div class="layui-col-lg6">-->
<#--                    <label class="layui-form-label">业务层名：</label>-->
<#--                    <div class="layui-input-block">-->
<#--                        <input type="text" name="serviceName" placeholder="请输入业务层名" autocomplete="off"-->
<#--                               class="layui-input" value="" >-->
<#--                    </div>-->
<#--                </div>-->

<#--                <div class="layui-col-lg6">-->
<#--                    <label class="layui-form-label">持久层名：</label>-->
<#--                    <div class="layui-input-block">-->
<#--                        <input type="text" name="daoName" placeholder="请输入持久层名" autocomplete="off"-->
<#--                               class="layui-input" value="" >-->
<#--                    </div>-->
<#--                </div>-->

            </div>
            <button type="button" class="layui-btn layui-btn-warm layui-btn-radius" id="generate_status_span">代码编译中，请等待...</button>
<#--            <div class="layui-form-item">-->
<#--                <div class="layui-input-block">-->
<#--                    <button class="layui-btn" lay-submit="" lay-filter="bond_sumbit">保存</button>-->
<#--                </div>-->
<#--            </div>-->
        </form>
    </div>
</script>

<script src="/layui/layui.js"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.define(['table', 'form', 'util', 'laytpl'], function (exports) {
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var admin = layui.admin;
        var laytpl = layui.laytpl;

        table.render({
            elem: '#test'
            ,url:'/generate/list'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sysName', width:80, title: '系统名'}
                // ,{field:'dbUrl', width:280, title: '数据库链接'}
                // ,{field:'userName', width:80, title: '用户名'}
                // ,{field:'userPass', width:80, title: '密码'}
                ,{field:'rootPath', width:180, title: '包路径'}
                ,{field:'generateStateDesc', width:180, title: '编译状态'}
                ,{field:'generateTimes', width:180, title: '编译次数'}
                , {title: '操作', align: 'center', toolbar: '#table-bondTemplate-lst', minWidth: 150}
            ]]
            , id: 'bondTemplateList'
            , page: true
        });

        //监听工具条
        table.on('tool(LAY-bondTemplate-lst-manage)', function (obj) {
            var data = obj.data;
            if (obj.event === 'bondTemplateDel') {  //删除

                $.ajax({
                    url: '/sysProject/delete/'+data.id,
                    type: 'DELETE',
                    success: function(res) {
                        if (res.code == '0') {
                            layer.msg("删除成功", function (index) {
                                obj.del()
                                layer.close(index);
                            });
                        } else {
                            layer.msg('删除失败！', {icon: 2, time: 1000});
                        }
                    }
                });
            } else if (obj.event === 'bondTemplateEdit') {
                data.list = layui.data('bondTemplateList').list;
                var content = $("#bondTemplateList").html();
                laytpl(content).render(data, function (string) {
                    layer.open({
                        type: 1,
                        title: "代码自动生成",
                        area: ['1200px', '400px'], //宽高
                        content: string
                    });
                    form.render('select');
                });

                $.ajax({
                    type : "GET",
                    url : "/generate/generateCode/"+data.id,
                    success : function(res) {
                        console.log("code: "+res.code);
                        console.log("code: "+(res.code != 0));
                        if(res.code != 0){
                            console.log("error: "+res);
                            setTimeout(function(){
                                if(stateTime){
                                    clearInterval(stateTime);
                                    layer.msg(res.msg);
                                }

                            }, 1800);
                        }
                    },
                    error : function(e){}
                });

                var stateTime = setInterval(function () {
                    checkGenerateStatus(data.id, stateTime);
                }, 500);
            }else if(obj.event === 'bondTemplateDownload'){
                window.open("/generate/download/"+data.id);
            }
            return false;
        });

        $(document).on('click', '#addbondTemplate', function () {
            debugger;
            var data = [];
            data.list = layui.data('bondTemplateList').list;
            var content = $("#bondTemplateList").html();
            laytpl(content).render(data, function (string) {
                layer.open({
                    type: 1,
                    title: "新增项目",
                    area: ['1200px', '400px'], //宽高
                    content: string
                });
                form.render('select');
            });
        });

        //搜索
        $("#reloadlstBond").on('click',function(){
            var name = $('#name').val();//名称
            table.reload('bondTemplateList', {
                method: 'get'
                , where: {sysName: name}
                , page: {
                    pageNo: 1
                }
            });
        });

        var checkGenerateStatus = function(id, stateTime){
            console.log(" : "+id);

            $.ajax({
                type : "GET",
                url : "/generate/status/"+id,
                success : function(res) {
                    if (res.code == '0') {

                        if(res.data == 3){
                            $("#generate_status_span").text("代码生成完毕！！！");
                            $("#generate_status_span").removeClass("layui-btn-warm").addClass("layui-btn-normal");
                            layer.msg(res.msg);
                            clearInterval(stateTime);
                            var name = $('#name').val();//名称
                            table.reload('bondTemplateList', {
                                method: 'get'
                                , where: {sysName: name}
                                , page: {
                                    pageNo: 1
                                }
                            });
                        }else{

                        }
                        // layer.msg('处理成功', {time: 1000}, function () {
                        //     layer.closeAll();
                        //     table.reload('bondTemplateList');
                        // });
                    } else {
                        clearInterval(stateTime);
                        // layer.msg(res.msg);
                    }
                    // console.log(result);
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }

        exports('template/bondTemplate', {})
    });

</script>

</body>
</html>
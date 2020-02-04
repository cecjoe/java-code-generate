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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>表数据</legend>
</fieldset>
<div class="layui-collapse" lay-filter="test">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">为什么JS社区大量采用未发布或者未广泛支持的语言特性？</h2>
        <div class="layui-colla-content">
            <p>有不少其他答案说是因为JS太差。我下面的答案已经说了，这不是根本性的原因。但除此之外，我还要纠正一些对JS具体问题的误解。JS当初是被作为脚本语言设计的，所以某些问题并不是JS设计得差或者是JS设计者的失误。比如var的作用域问题，并不是“错误”，而是当时绝大部分脚本语言都是这样的，如perl/php/sh等。模块的问题也是，脚本语言几乎都没有模块/命名空间功能。弱类型、for-in之类的问题也是，只不过现在用那些老的脚本语言的人比较少，所以很多人都误以为是JS才有的坑。另外有人说JS是半残语言，满足不了开发需求，1999年就该死。半残这个嘛，就夸张了。JS虽然有很多问题，但是设计总体还是优秀的。——来自知乎@贺师俊</p>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">为什么前端工程师多不愿意用 Bootstrap 框架？</h2>
        <div class="layui-colla-content">
            <p>因为不适合。如果希望开发长期的项目或者制作产品类网站，那么就需要实现特定的设计，为了在维护项目中可以方便地按设计师要求快速修改样式，肯定会逐步编写出各种业务组件、工具类，相当于为项目自行开发一套框架。——来自知乎@Kayo</p>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">layui 更适合哪些开发者？</h2>
        <div class="layui-colla-content">
            <p>在前端技术快速变革的今天，layui 仍然坚持语义化的组织模式，甚至于模块理念都是采用类AMD组织形式，并非是有意与时代背道而驰。layui 认为以jQuery为核心的开发方式还没有到完全消亡的时候，而早期市面上基于jQuery的UI都普通做得差强人意，所以需要有一个新的UI去重新为这一领域注入活力，并采用一些更科学的架构方式。
                <br><br>
                因此准确地说，layui 更多是面向那些追求开发简单的前端工程师们，以及所有层次的服务端程序员。</p>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">贤心是男是女？</h2>
        <div class="layui-colla-content">
            <p>man！ 所以这个问题不要再出现了。。。</p>
        </div>
    </div>
</div>
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
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bondTemplateEdit">关联设置</a>
            </script>
        </div>
    </div>
</div>

<script type="text/template" id="bondTemplateList">

<#--    <div class="layui-card-body" style="padding: 15px;">-->
<#--        <form class="layui-form" lay-filter="component-form-group" id="submits" onsubmit="return false">-->
<#--            <div class="layui-row layui-col-space10 layui-form-item">-->
<#--                <input type="hidden" name="id" value="{{ d.id }}">-->
<#--                <div class="layui-col-lg12">-->
<#--                    <label class="layui-form-label">项目名称：</label>-->
<#--                    <div class="layui-input-block">-->
<#--                        <input type="text" name="sysName" placeholder="请输入名称" autocomplete="off"-->
<#--                               lay-verify="required" class="layui-input"-->
<#--                               value="{{ d.sysName?d.sysName:'' }}" {{# if(d.id){ }}disabled{{# } }}>-->
<#--                    </div>-->
<#--                </div>-->

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
<#--                                class="layui-input" value="" >-->
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

<#--            </div>-->
<#--            <div class="layui-form-item">-->
<#--                <div class="layui-input-block">-->
<#--                    <button class="layui-btn" lay-submit="" lay-filter="bond_sumbit">保存</button>-->
<#--                </div>-->
<#--            </div>-->
<#--        </form>-->
<#--    </div>-->
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
            ,url:'/sysProject/list'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sysName', width:80, title: '系统名'}
                ,{field:'dbUrl', width:280, title: '数据库链接'}
                ,{field:'userName', width:80, title: '用户名'}
                ,{field:'userPass', width:80, title: '密码'}
                ,{field:'rootPath', width:180, title: '包路径'}
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
            }
            return false;
        });

        /* 监听提交 */
        form.on('submit(bond_sumbit)', function (data) {
            var url = '/project/savePreProcess';
            var type = 'put';

            $.ajax({
                type : type,
                contentType: "application/json;charset=UTF-8",
                url : url,
                data : JSON.stringify(data.field),
                success : function(res) {
                    if (res.code == '0') {
                        layer.msg('处理成功', {time: 1000}, function () {
                            layer.closeAll();
                            table.reload('bondTemplateList');
                        });
                    } else {
                        layer.msg(res.msg);
                    }
                    // console.log(result);
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
            return false;
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

        exports('template/bondTemplate', {})
    });

    layui.use(['element', 'layer'], function(){
        var element = layui.element;
        var layer = layui.layer;

        //监听折叠
        element.on('collapse(test)', function(data){
            layer.msg('展开状态：'+ data.show);
        });
    });
</script>

</body>
</html>
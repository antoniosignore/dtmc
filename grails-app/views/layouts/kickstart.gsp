<!DOCTYPE html>
<%-- <html lang="${org.springframework.web.servlet.support.RequestContextUtils.getLocale(request).toString().replace('_', '-')}"> --%>
<html lang="${session.'org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'}">

<head>
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="shortcut icon" href="${resource(plugin: 'kickstart-with-bootstrap', dir: 'images', file: 'favicon.ico')}"
          type="image/x-icon"/>

    <%-- Manual switch for the skin can be found in /view/_menu/_config.gsp --%>
    <r:require modules="jquery"/>
    <r:require modules="bootstrap"/>
    <r:require modules="bootstrap_utils"/>
    <r:require modules="raphael"/>
    <r:require modules="application"/>

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

    <%-- For Javascript see end of body --%>

    <style type="text/css">
    .item {
        width: 25%;
    }

    .item.w2 {
        width: 50%;
    }
    </style>

    <r:external uri="/js/masonry.pkgd.min.js"/>

    <r:layoutResources/>
    <g:layoutHead/>

    <r:external uri="/css/style-sortable.css"/>

    <r:external uri="/js/alert.js"/>

    <r:external uri="/js/maxlength/bootstrap-maxlength.min.js"/>

    <r:external uri="/js/select2/select2.min.js"/>
    <r:external uri="/js/select2/select2.css"/>
    <r:external uri="/js/select2/select2-bootstrap.css"/>

    <r:external uri="/js/bootstrap-checkbox/js/bootstrap-checkbox.js"/>
    <r:external uri="/js/bootstrap-checkbox/css/bootstrap-checkbox.css"/>

    <r:external uri="/css/image-picker.css"/>
    <r:external uri="/js/image-picker.min.js"/>
    <r:external uri="/css/ssp.css"/>

    <r:external uri="/js/html5.js"/>

</head>

<body data-grid-framework="bo"
      data-grid-color="#00CCDD"
      data-grid-opacity="0.5" data-grid-zindex="10"
      data-grid-nbcols="16">

<g:render template="/_menu/navbar"/>

<!-- Enable to overwrite Header by individual page -->
<g:if test="${pageProperty(name: 'page.header')}">
    <g:pageProperty name="page.header"/>
</g:if>
<g:else>
    <g:render template="/layouts/header"/>
</g:else>

<div class="container-fluid">
    <div class="row-fluid">
        <g:render template="/layouts/leftbar"/>
        <g:render template="/layouts/content"/>
        %{--<g:render template="/layouts/rightbar"/>--}%
    </div>

</div>

<g:if test="${pageProperty(name: 'page.footer')}">
    <g:pageProperty name="page.footer"/>
</g:if>
<g:else>
    <g:render template="/layouts/footer"/>
</g:else>

%{--<!-- Enable to insert additional components (e.g., modals, javascript, etc.) by any individual page -->--}%
%{--<g:if test="${pageProperty(name: 'page.include.bottom')}">--}%
%{--<g:pageProperty name="page.include.bottom"/>--}%
%{--</g:if>--}%
%{--<g:else>--}%
%{--<!-- Insert a modal dialog for registering (for an open site registering is possible on any page) -->--}%
%{--<g:render template="/_common/modals/registerDialog" model="[item: item]"/>--}%
%{--</g:else>--}%

<r:layoutResources/>
</body>

</html>


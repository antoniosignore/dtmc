<!-- Main menu in one row (e.g., controller entry points -->
<div class="span9">
<!-- print system messages (infos, warnings, etc) - not validation errors -->

    <g:if test="${flash.message && !layout_noflashmessage}">
        <div class="alert alert-info">${flash.message}</div>
    </g:if>
    <g:set var="pagetitle"
           value="${message(code: params.controller + '.' + params.action + '.custom.title.label', default: params.controller + '.' + params.action + '.custom.title.label NOT DEFINED')}"/>
    <h3>${pagetitle}</h3>
    <g:if test="${!layout_nosecondarymenu}">
        <div class="row-fluid">
            <div class="span12">
                <g:render template="/_menu/submenubar"/>
            </div>
        </div>
    </g:if>

%{--<g:render template="/_menu/promosubmenubar"/>--}%

    <g:layoutBody/>
    <g:pageProperty name="page.body"/>
</div><!--/span-->

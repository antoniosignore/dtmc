<%@ page import="com.vodafone.global.blueprint.promo.common.Constants" %>
<div class="span3">
    <div class="well sidebar-nav" style="padding: 8px 0;">
        <ul class="nav nav-list ">

            <li class="nav-header">Navigation</li>

            <li class="${'portfolio'.equals(params.controller) ? 'active' : ''}">
                <g:link controller="portfolio" action="list">Portfolio</g:link>
            </li>

            <li class="${'stock'.equals(params.controller) ? 'active' : ''}">
                <g:link controller="stock" action="list">Stock</g:link>
            </li>

            <li class="${'userIndicators'.equals(params.controller) ? 'active' : ''}">
                <g:link controller="userIndicators" action="list">User Indicators</g:link>
            </li>

            <li class="${'indicators'.equals(params.controller) ? 'active' : ''}">
                <g:link controller="indicators" action="list">Indicators</g:link>
            </li>

        </ul>
    </div>
</div>


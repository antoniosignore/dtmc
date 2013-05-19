<%@ page import="com.netnumeri.server.finance.finpojo.PortfolioItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'portfolioItem.label', default: 'PortfolioItem')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-portfolioItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-portfolioItem" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list portfolioItem">

        <g:if test="${portfolioItemInstance?.amount}">
            <li class="fieldcontain">
                <span id="amount-label" class="property-label"><g:message code="portfolioItem.amount.label"
                                                                          default="Amount"/></span>

                <span class="property-value" aria-labelledby="amount-label"><g:fieldValue
                        bean="${portfolioItemInstance}" field="amount"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioItemInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="portfolioItem.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue
                        bean="${portfolioItemInstance}" field="description"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioItemInstance?.instrument}">
            <li class="fieldcontain">
                <span id="instrument-label" class="property-label"><g:message code="portfolioItem.instrument.label"
                                                                              default="Instrument"/></span>

                <span class="property-value" aria-labelledby="instrument-label"><g:link controller="instrument"
                                                                                        action="show"
                                                                                        id="${portfolioItemInstance?.instrument?.id}">${portfolioItemInstance?.instrument?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${portfolioItemInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="portfolioItem.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${portfolioItemInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioItemInstance?.portfolio}">
            <li class="fieldcontain">
                <span id="portfolio-label" class="property-label"><g:message code="portfolioItem.portfolio.label"
                                                                             default="Portfolio"/></span>

                <span class="property-value" aria-labelledby="portfolio-label"><g:link controller="portfolio"
                                                                                       action="show"
                                                                                       id="${portfolioItemInstance?.portfolio?.id}">${portfolioItemInstance?.portfolio?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${portfolioItemInstance?.id}"/>
            <g:link class="edit" action="edit" id="${portfolioItemInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>

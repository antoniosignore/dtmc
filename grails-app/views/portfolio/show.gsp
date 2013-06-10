<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'portfolio.label', default: 'Portfolio')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-portfolio" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-portfolio" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list portfolio">

        <g:if test="${portfolioInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="portfolio.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${portfolioInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="portfolio.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue
                        bean="${portfolioInstance}" field="description"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioInstance?.items}">
            <li class="fieldcontain">
                <span id="items-label" class="property-label"><g:message code="portfolio.items.label"
                                                                         default="Items"/></span>

                <g:each in="${portfolioInstance.items}" var="i">
                    <span class="property-value" aria-labelledby="items-label"><g:link controller="portfolioItem"
                                                                                       action="show"
                                                                                       id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${portfolioInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="portfolio.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${portfolioInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${portfolioInstance?.transactions}">
            <li class="fieldcontain">
                <span id="transactions-label" class="property-label"><g:message code="portfolio.transactions.label"
                                                                                default="Transactions"/></span>

                <g:each in="${portfolioInstance.transactions}" var="t">
                    <span class="property-value" aria-labelledby="transactions-label"><g:link controller="trade"
                                                                                              action="show"
                                                                                              id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${portfolioInstance?.id}"/>
            <g:link class="edit" action="edit" id="${portfolioInstance?.id}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>

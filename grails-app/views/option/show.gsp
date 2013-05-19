<%@ page import="com.netnumeri.server.finance.finpojo.derivative.equity.Option" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'option.label', default: 'Option')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-option" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-option" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list option">

        <g:if test="${optionInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="option.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${optionInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${optionInstance?.interestRate}">
            <li class="fieldcontain">
                <span id="interestRate-label" class="property-label"><g:message code="option.interestRate.label"
                                                                                default="Interest Rate"/></span>

                <span class="property-value" aria-labelledby="interestRate-label"><g:fieldValue bean="${optionInstance}"
                                                                                                field="interestRate"/></span>

            </li>
        </g:if>

        <g:if test="${optionInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="option.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${optionInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${optionInstance?.id}"/>
            <g:link class="edit" action="edit" id="${optionInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>

<%@ page import="com.netnumeri.server.finance.finpojo.Persistable" %>



<div class="fieldcontain ${hasErrors(bean: persistableInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="persistable.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${persistableInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: persistableInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="persistable.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${persistableInstance?.name}"/>
</div>


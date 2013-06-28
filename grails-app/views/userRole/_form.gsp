<%@ page import="com.dtmc.security.UserRole" %>



<div class="control-group fieldcontain ${hasErrors(bean: userRoleInstance, field: 'role', 'error')} ">
    <label for="role" class="control-label"><g:message
            code="userRole.role.label"
            default="Role"/></label>

    <div class="controls">
        <g:select id="role" name="role.id" from="${com.dtmc.security.Role.list()}" optionKey="id"
                  value="${userRoleInstance?.role?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: userRoleInstance, field: 'role', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userRoleInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="userRole.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id"
                  value="${userRoleInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: userRoleInstance, field: 'user', 'error')}</span>
    </div>
</div>


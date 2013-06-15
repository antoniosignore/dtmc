<%@ page import="com.dtmc.security.UserBean" %>



<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'fname', 'error')} ">
    <label for="fname" class="control-label"><g:message
            code="userBean.fname.label"
            default="Fname"/></label>

    <div class="controls">
        <g:textField name="fname" maxlength="30" value="${userBeanInstance?.fname}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'fname', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'lname', 'error')} ">
    <label for="lname" class="control-label"><g:message
            code="userBean.lname.label"
            default="Lname"/></label>

    <div class="controls">
        <g:textField name="lname" maxlength="30" value="${userBeanInstance?.lname}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'lname', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'email', 'error')} ">
    <label for="email" class="control-label"><g:message
            code="userBean.email.label"
            default="Email"/></label>

    <div class="controls">
        <g:field type="email" name="email" value="${userBeanInstance?.email}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'email', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'phone', 'error')} ">
    <label for="phone" class="control-label"><g:message
            code="userBean.phone.label"
            default="Phone"/></label>

    <div class="controls">
        <g:textField name="phone" maxlength="15" value="${userBeanInstance?.phone}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'phone', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'mobile', 'error')} ">
    <label for="mobile" class="control-label"><g:message
            code="userBean.mobile.label"
            default="Mobile"/></label>

    <div class="controls">
        <g:textField name="mobile" maxlength="15" value="${userBeanInstance?.mobile}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'mobile', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'city', 'error')} ">
    <label for="city" class="control-label"><g:message
            code="userBean.city.label"
            default="City"/></label>

    <div class="controls">
        <g:textField name="city" maxlength="20" value="${userBeanInstance?.city}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'city', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'state', 'error')} ">
    <label for="state" class="control-label"><g:message
            code="userBean.state.label"
            default="State"/></label>

    <div class="controls">
        <g:textField name="state" maxlength="20" value="${userBeanInstance?.state}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'state', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'country', 'error')} ">
    <label for="country" class="control-label"><g:message
            code="userBean.country.label"
            default="Country"/></label>

    <div class="controls">
        <g:textField name="country" maxlength="20" value="${userBeanInstance?.country}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'country', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'address1', 'error')} ">
    <label for="address1" class="control-label"><g:message
            code="userBean.address1.label"
            default="Address1"/></label>

    <div class="controls">
        <g:textField name="address1" maxlength="200" value="${userBeanInstance?.address1}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'address1', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'username', 'error')} ">
    <label for="username" class="control-label"><g:message
            code="userBean.username.label"
            default="Username"/></label>

    <div class="controls">
        <g:textField name="username" value="${userBeanInstance?.username}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'username', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'password', 'error')} ">
    <label for="password" class="control-label"><g:message
            code="userBean.password.label"
            default="Password"/></label>

    <div class="controls">
        <g:textField name="password" value="${userBeanInstance?.password}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'password', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'accountExpired', 'error')} ">
    <label for="accountExpired" class="control-label"><g:message
            code="userBean.accountExpired.label"
            default="Account Expired"/></label>

    <div class="controls">
        <bs:checkBox name="accountExpired" value="${userBeanInstance?.accountExpired}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'accountExpired', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'accountLocked', 'error')} ">
    <label for="accountLocked" class="control-label"><g:message
            code="userBean.accountLocked.label"
            default="Account Locked"/></label>

    <div class="controls">
        <bs:checkBox name="accountLocked" value="${userBeanInstance?.accountLocked}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'accountLocked', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'address2', 'error')} ">
    <label for="address2" class="control-label"><g:message
            code="userBean.address2.label"
            default="Address2"/></label>

    <div class="controls">
        <g:textField name="address2" value="${userBeanInstance?.address2}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'address2', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'company', 'error')} ">
    <label for="company" class="control-label"><g:message
            code="userBean.company.label"
            default="Company"/></label>

    <div class="controls">
        <g:textField name="company" value="${userBeanInstance?.company}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'company', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'enabled', 'error')} ">
    <label for="enabled" class="control-label"><g:message
            code="userBean.enabled.label"
            default="Enabled"/></label>

    <div class="controls">
        <bs:checkBox name="enabled" value="${userBeanInstance?.enabled}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'enabled', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'lastLoginDate', 'error')} ">
    <label for="lastLoginDate" class="control-label"><g:message
            code="userBean.lastLoginDate.label"
            default="Last Login Date"/></label>

    <div class="controls">
        <bs:datePicker name="lastLoginDate" precision="day" value="${userBeanInstance?.lastLoginDate}" default="none"
                       noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'lastLoginDate', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'passwordExpired', 'error')} ">
    <label for="passwordExpired" class="control-label"><g:message
            code="userBean.passwordExpired.label"
            default="Password Expired"/></label>

    <div class="controls">
        <bs:checkBox name="passwordExpired" value="${userBeanInstance?.passwordExpired}"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'passwordExpired', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userBeanInstance, field: 'registerDate', 'error')} ">
    <label for="registerDate" class="control-label"><g:message
            code="userBean.registerDate.label"
            default="Register Date"/></label>

    <div class="controls">
        <bs:datePicker name="registerDate" precision="day" value="${userBeanInstance?.registerDate}" default="none"
                       noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: userBeanInstance, field: 'registerDate', 'error')}</span>
    </div>
</div>


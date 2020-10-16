# DEMO app for CAS SLO (Single Log Out)

https://apereo.github.io/cas/6.2.x/installation/Logout-Single-Signout.html

CAS sends an HTTP POST message directly to the service.
A sample back channel SLO message:

```xml
<samlp:LogoutRequest
    xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol"
    xmlns:saml="urn:oasis:names:tc:SAML:2.0:assertion"
    ID="[RANDOM ID]"
    Version="2.0"
    IssueInstant="[CURRENT DATE/TIME]">
    <saml:NameID>[USER IDENTIFICATION ID (personal code)]</saml:NameID>
    <samlp:SessionIndex>[SESSION IDENTIFIER]</samlp:SessionIndex>
</samlp:LogoutRequest>
```

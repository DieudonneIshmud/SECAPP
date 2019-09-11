package util;
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class UCTauthentication 
{
    public static boolean authenticate(String id, String password) {
        boolean answer = false;
        //id = "123";
        //password = "123";
        
        System.out.println(id);
        System.out.println(password);

        Hashtable<String, String> ldapEnv = new Hashtable<String, String>();
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapEnv.put(Context.PROVIDER_URL, "ldaps://srvslsidm001.uct.ac.za:636");
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldapEnv.put(Context.SECURITY_PRINCIPAL, "cn=" + id + ",ou=users,o=uct");
        ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
        ldapEnv.put("java.naming.ldap.factory.socket", BlindSSLSocketFactoryTest.class.getName());

        try {
            LdapContext ctx = new InitialLdapContext(ldapEnv, null);
            answer = true;
            System.out.println("Access granted");
        } catch (AuthenticationException e) {
            answer = false;
            System.out.println("The credentials could not be validated!");
        } catch (NamingException e) {
            answer = false;
            e.printStackTrace();
        }
        return answer;
    }
}
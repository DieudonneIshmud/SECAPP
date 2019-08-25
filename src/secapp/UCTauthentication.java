import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class UCTauthentication 
{
	public static void main(String[] args)
	{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your ID:");
			String id = scanner.next();
			System.out.println("Enter your password:");
			String password = scanner.next();
		
			Hashtable<String, String> ldapEnv = new Hashtable<String, String>();
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.PROVIDER_URL, "ldaps://srvslsidm001.uct.ac.za:636");
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, "cn="+id+",ou=users,o=uct");
			ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
			ldapEnv.put("java.naming.ldap.factory.socket", BlindSSLSocketFactoryTest.class.getName());
			

			try 
			{
				LdapContext ctx = new InitialLdapContext(ldapEnv, null);
				System.out.println("Successfull bind to UCT network!");
			} 
			catch (AuthenticationException e) 
				{System.out.println("The credentials could not be validated!");}
			catch (NamingException e) 
				{e.printStackTrace();}
	}
}
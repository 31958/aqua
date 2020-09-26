package com.aqua.data.oauth;

import org.dmfs.httpessentials.client.HttpRequestExecutor;
import org.dmfs.httpessentials.exceptions.ProtocolError;
import org.dmfs.httpessentials.exceptions.ProtocolException;
import org.dmfs.httpessentials.httpurlconnection.HttpUrlConnectionExecutor;
import org.dmfs.oauth2.client.BasicOAuth2AuthorizationProvider;
import org.dmfs.oauth2.client.BasicOAuth2Client;
import org.dmfs.oauth2.client.BasicOAuth2ClientCredentials;
import org.dmfs.oauth2.client.OAuth2AccessToken;
import org.dmfs.oauth2.client.OAuth2AuthorizationProvider;
import org.dmfs.oauth2.client.OAuth2Client;
import org.dmfs.oauth2.client.OAuth2ClientCredentials;
import org.dmfs.oauth2.client.OAuth2InteractiveGrant;
import org.dmfs.oauth2.client.grants.AuthorizationCodeGrant;
import org.dmfs.oauth2.client.grants.ImplicitGrant;
import org.dmfs.oauth2.client.grants.ResourceOwnerPasswordGrant;
import org.dmfs.oauth2.client.scope.BasicScope;
import org.dmfs.rfc3986.encoding.Precoded;
import org.dmfs.rfc3986.uris.LazyUri;
import org.dmfs.rfc5545.Duration;

import java.io.IOException;
import java.net.URI;

public class OAuthUtil{
    static OAuth2Client client;
    static HttpRequestExecutor executor;

    public static void initialize(String authorize, String token, String clientId, String clientSecret, String redirect) {
        // Create HttpRequestExecutor to execute HTTP requests
        executor = new HttpUrlConnectionExecutor();
        // Any other HttpRequestExecutor implementaion will do

        // Create OAuth2 provider
        OAuth2AuthorizationProvider provider = new BasicOAuth2AuthorizationProvider(
                URI.create(authorize),
                URI.create(token),
                new Duration(1,0,3600) /* default expiration time in case the server doesn't return any */);

        // Create OAuth2 client credentials
        OAuth2ClientCredentials credentials = new BasicOAuth2ClientCredentials(
                clientId, clientSecret);

        // Create OAuth2 client
        client = new BasicOAuth2Client(
                provider,
                credentials,
                new LazyUri(new Precoded(redirect)) /* Redirect URL */);
    }

    public static void authorizationCodeGrant(String redirect) {
        // Start an interactive Authorization Code Grant
        OAuth2InteractiveGrant grant = new AuthorizationCodeGrant(
                client, new BasicScope("scope"));

        // Get the authorization URL and open it in a WebView
        URI authorizationUrl = grant.authorizationUrl();

        // Open the URL in a WebView and wait for the redirect to the redirect URL
        // After the redirect, feed the URL to the grant to retrieve the access token
        Precoded redirectUrl = new Precoded(redirect);
        try {
            OAuth2AccessToken token = grant.withRedirect(new LazyUri(redirectUrl)).accessToken(executor);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProtocolError protocolError) {
            protocolError.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    public static void implicitGrant(String redirect){
        // Start an interactive Implicit Grant
        OAuth2InteractiveGrant grant = new ImplicitGrant(client, new BasicScope("scope"));

        // Get the authorization URL and open it in a WebView
        URI authorizationUrl = grant.authorizationUrl();
        Precoded redirectUrl = new Precoded(redirect);

        // Open the URL in a WebView and wait for the redirect to the redirect URL
        // After the redirect, feed it to the grant to retrieve the access token
        try {
            OAuth2AccessToken token = grant.withRedirect(new LazyUri(redirectUrl)).accessToken(executor);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProtocolError protocolError) {
            protocolError.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    public static void userResourceGrant (String username, String password){
        // Request access token using a Resource Owner Password Grant
        try {
            OAuth2AccessToken token = new ResourceOwnerPasswordGrant(
                    client, new BasicScope("scope"), username, password).accessToken(executor);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProtocolError protocolError) {
            protocolError.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }
}

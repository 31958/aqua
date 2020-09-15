package com.aqua.data.oauth;

import org.dmfs.httpessentials.client.HttpRequestExecutor;
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
import org.dmfs.oauth2.client.scope.BasicScope;
import org.dmfs.rfc3986.encoding.Precoded;
import org.dmfs.rfc3986.uris.LazyUri;

import java.net.URI;
import java.time.Duration;

public class Connect {
    public static void main(String[] args) {
        // Create HttpRequestExecutor to execute HTTP requests
        // Any other HttpRequestExecutor implementaion will do
        HttpRequestExecutor executor = new HttpUrlConnectionExecutor();

        // Create OAuth2 provider
        OAuth2AuthorizationProvider provider = new BasicOAuth2AuthorizationProvider(
                URI.create("http://example.com/auth"),
                URI.create("http://example.com/token"),
                new Duration(1,0,3600) /* default expiration time in case the server doesn't return any */);

        // Create OAuth2 client credentials
        OAuth2ClientCredentials credentials = new BasicOAuth2ClientCredentials(
                "client-id", "client-password");

        // Create OAuth2 client
        OAuth2Client client = new BasicOAuth2Client(
                provider,
                credentials,
                new LazyUri(new Precoded("http://localhost")) /* Redirect URL */);

        // Start an interactive Authorization Code Grant
        OAuth2InteractiveGrant grant = new AuthorizationCodeGrant(
                client, new BasicScope("scope"));

        // Get the authorization URL and open it in a WebView
        URI authorizationUrl = grant.authorizationUrl();

        // Open the URL in a WebView and wait for the redirect to the redirect URL
        // After the redirect, feed the URL to the grant to retrieve the access token
        OAuth2AccessToken token = grant.withRedirect(redirectUrl).accessToken(executor);

        // Start an interactive Implicit Grant
        OAuth2InteractiveGrant grant = new ImplicitGrant(client, new BasicScope("scope"));

        // Get the authorization URL and open it in a WebView
        URI authorizationUrl = grant.authorizationUrl();

        // Open the URL in a WebView and wait for the redirect to the redirect URL
        // After the redirect, feed it to the grant to retrieve the access token
        OAuth2AccessToken token = grant.withRedirect(redirectUrl).accessToken(executor);
    }
}

package com.darcstarsolutions.finance.brokers.etrade;

import com.etrade.etws.oauth.sdk.client.IOAuthClient;
import com.etrade.etws.oauth.sdk.client.OAuthClientImpl;
import com.etrade.etws.oauth.sdk.common.Token;
import com.etrade.etws.sdk.client.ClientRequest;
import com.etrade.etws.sdk.client.Environment;
import com.etrade.etws.sdk.common.ETWSException;

import java.io.IOException;

/**
 * Created by mharris021 on 9/10/14.
 */
public abstract class EtradeAuthorizationTemplate {
    private IOAuthClient client = null;
    private ClientRequest request = null;
    private Token token = null;
    private String oauth_consumer_key = null; // Your consumer key
    private String oauth_consumer_secret = null; // Your consumer secret
    private String oauth_request_token = null; // Request token
    private String oauth_request_token_secret = null; // Request token secret
    private Environment environment;

    private void init() {

        client = OAuthClientImpl.getInstance(); // Instantiate IOAUthClient
        request = new ClientRequest(); // Instantiate ClientRequest

        request.setEnv(Environment.SANDBOX); // Use sandbox environment

        request.setConsumerKey(oauth_consumer_key); //Set consumer key
        request.setConsumerSecret(oauth_consumer_secret); // Set consumer secret
        try {
            token = client.getRequestToken(request); // Get request-token object
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ETWSException e) {
            e.printStackTrace();
        }
        oauth_request_token = token.getToken(); // Get token string
        oauth_request_token_secret = token.getSecret(); // Get token secret

    }

}

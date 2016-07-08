package org.apache.http.impl.auth;

/**
 * @author grabslu
 */
public class NTLMEnginePublic {

    private final NTLMEngineImpl engine = new NTLMEngineImpl();

    public String generateType1Msg(String domain, String workstation) throws NTLMEngineException {
        return engine.generateType1Msg(domain, workstation);
    }

    public String generateType3Msg(String username, String password, String domain, String workstation, String challenge) throws NTLMEngineException {
        return engine.generateType3Msg(username, password, domain, workstation, challenge);
    }
}

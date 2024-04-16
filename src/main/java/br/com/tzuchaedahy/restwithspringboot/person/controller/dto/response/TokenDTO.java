package br.com.tzuchaedahy.restwithspringboot.person.controller.dto.response;

import java.util.Date;
import java.util.Objects;

public class TokenDTO {
    private String userName;
    private boolean authenticated;
    private Date createdAt;
    private Date expiresAt;
    private String accessToken;
    private String refreshToken;

    public TokenDTO() {
    }

    public TokenDTO(String userName, boolean authenticated, Date createdAt, Date expiresAt, String accessToken, String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return authenticated == tokenDTO.authenticated && Objects.equals(userName, tokenDTO.userName) && Objects.equals(createdAt, tokenDTO.createdAt) && Objects.equals(expiresAt, tokenDTO.expiresAt) && Objects.equals(accessToken, tokenDTO.accessToken) && Objects.equals(refreshToken, tokenDTO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, authenticated, createdAt, expiresAt, accessToken, refreshToken);
    }
}

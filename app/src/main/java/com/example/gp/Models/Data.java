
package com.example.gp.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("userable_id")
    @Expose
    private Integer userableId;
    @SerializedName("userable_type")
    @Expose
    private String userableType;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userable")
    @Expose
    private Userable userable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserableId() {
        return userableId;
    }

    public void setUserableId(Integer userableId) {
        this.userableId = userableId;
    }

    public String getUserableType() {
        return userableType;
    }

    public void setUserableType(String userableType) {
        this.userableType = userableType;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Userable getUserable() {
        return userable;
    }

    public void setUserable(Userable userable) {
        this.userable = userable;
    }

}

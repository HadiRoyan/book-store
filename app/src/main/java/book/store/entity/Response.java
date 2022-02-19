/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.store.entity;

public enum Response {
    SUCCESS("Ok"),
    FAILED("FAILED"),
    ERROR("ERROR");

    private String description;

    Response(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

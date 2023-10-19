package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class Controlador {

    @PostMapping("/register_identity")
    public Identity register_identity(@RequestBody Identity identity) throws SQLException, ClassNotFoundException {

        String document = identity.getDocument();
        String name = identity.getName();
        String cellphone = identity.getCellphone();
        String address = identity.getAddress();

        if (document == null || document.equals("") || document.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                cellphone == null || cellphone.equals("") || cellphone.length() < 0 || address == null || address.equals("") ||
                address.length() < 0) {

            return new Identity(null, null, null, null);
        } else {
            BD bd = new BD();
            identity = bd.register(document, name, cellphone, address);
        }
        return identity;
    }

    @PostMapping("/register_relations")
    public Relations register_relations(@RequestBody Relations relations) throws SQLException, ClassNotFoundException {

        String name = relations.getName();
        String ally = relations.getAlly();
        String document_identity = relations.getDocument_identity();


        if (name == null || name.equals("") || name.length() < 0 || ally == null || ally.equals("") || ally.length() < 0 ||
                document_identity == null || document_identity.equals("") || document_identity.length() < 0) {
            return new Relations(null, null, null);
        } else {
            BD bd = new BD();
            String document_Bd = BD.select_document(document_identity);

            if (document_Bd.equals("")) {
                return new Relations(null, Errors.error_document, null);
            } else {
                bd = new BD();
                relations = bd.register_relations(name, ally, document_identity);
            }
        }
        return relations;
    }

    @GetMapping("/search_identity")
    public List<Identity> search_identity() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Identity> list = bd.search_identity();

        return list;
    }

    @GetMapping("/search_relations")
    public List<Relations> search_relations() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Relations> list = bd.search_relations();

        return list;
    }
}

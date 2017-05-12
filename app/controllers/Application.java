package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.Arrays;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        String folderPath = Play.application()
                .configuration()
                .getString("folder");
        
        File dir = new File(folderPath);
        String[] files = dir.list();
        return ok(viewer.render(Arrays.asList(files)));
    }
    
    public static Result files() {
        String folderPath = Play.application()
                .configuration()
                .getString("folder");
        
        File dir = new File(folderPath);
        String[] files = dir.list();
        
        ObjectNode rootJson = Json.newObject();
        ArrayNode arr = rootJson.arrayNode();
        
        for(String file : files) {
            arr.add(file);
        }
        
        rootJson.putArray("files").addAll(arr);
        return ok(rootJson);
    }
}

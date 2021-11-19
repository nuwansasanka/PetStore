package com.microprofile.PetStorenew;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/petsType")
@Produces("application/json")
public class PetTypeResource {

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetsType() {

        return Response.ok(PetTypeDataSingleton.getInstance().getArrayList()).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet Type found for the id.") })
    @GET
    @Path("{petTypeId}")
    public Response getPet(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        PetType pet = new PetType();
        pet.setPetTypeId(petTypeId);
        pet.setPetType("Dog");

        return Response.ok(pet).build();

    }

    @Path("/add")
    @POST
    public Response addPetType(String requestget) throws JSONException {
        JSONObject jsonobject=new JSONObject(requestget);

        PetType pet = new  PetType();
        List<PetType> pets = new ArrayList<PetType>();
        List<PetType> temp = new ArrayList<PetType>();

        if(jsonobject.has("type")){
            String petType = jsonobject.getString("type");

            pet.setPetTypeId(PetTypeDataSingleton.getInstance().getArrayList().get(PetTypeDataSingleton.getInstance().getArrayList().size()-1).getPetTypeId()+1);

            pet.setPetType(petType);
            pets.add(pet);
            temp.addAll(PetTypeDataSingleton.getInstance().getArrayList());
            temp.addAll(pets);
            PetTypeDataSingleton.getInstance().setArrayList(temp);
            return Response.ok(pet).build();
        }
        else{
            return Response.ok("{\n" + "\"successful\":false\n" + "}").build();

        }


    }

    @PUT
    @Produces("application/json")
    @Path("/edit/{petTypeId}")
    public Response editPetType(@PathParam("petTypeId") int petTypeId,String petData) throws JSONException {
        JSONObject jsonObject = new JSONObject(petData);
        boolean isUpdated = false;
        int id = 0;
        if(jsonObject.has("type")){
            for (int i=0;i<PetTypeDataSingleton.getInstance().getArrayList().size();i++){
                if(petTypeId == PetTypeDataSingleton.getInstance().getArrayList().get(i).getPetTypeId()){
                    PetTypeDataSingleton.getInstance().getArrayList().get(i).setPetType(jsonObject.getString("type"));
                    isUpdated=true;
                    id = i;
                }
            }
        }
//        if(jsonObject.has("age")){
//            for (int i=0;i<Petdatasingleton.getInstance().getArrayList().size();i++){
//                if(petTypeId == Petdatasingleton.getInstance().getArrayList().get(i).getPetId()){
//                    Petdatasingleton.getInstance().getArrayList().get(i).setPetAge(Integer.parseInt(jsonObject.getString("age")));
//                    isUpdated=true;
//                    id = i;
//                }
//            }
//        }
//        if(jsonObject.has("type")){
//            for (int i=0;i<Petdatasingleton.getInstance().getArrayList().size();i++){
//                if(petTypeId == Petdatasingleton.getInstance().getArrayList().get(i).getPetId()){
//                    Petdatasingleton.getInstance().getArrayList().get(i).setPetType(jsonObject.getString("type"));
//                    isUpdated=true;
//                    id = i;
//                }
//            }
//        }
        if(isUpdated){
            return Response.ok(PetTypeDataSingleton.getInstance().getArrayList().get(id)).build();
        }else{
            return Response.ok("{\n" + "\"success\":false\n" + "}").build();
        }

    }
    @DELETE
    @Produces("application/json")
    @Path("/delete/{petTypeId}")
    public Response deletePet(@PathParam("petTypeId") int petTypeId){
        boolean isPetFound = false;
        for (int i=0;i<PetTypeDataSingleton.getInstance().getArrayList().size();i++){
            if(petTypeId == PetTypeDataSingleton.getInstance().getArrayList().get(i).getPetTypeId()){
                PetTypeDataSingleton.getInstance().getArrayList().remove(i);
                isPetFound = true;
            }
        }
        if(isPetFound){
            return Response.ok("{\n" + "\"successful\":true\n" + "}").build();
        }else{
            return Response.ok("{\n" + "\"successful\":false\n" + "}").build();
        }

    }


}

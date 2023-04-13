package com.anggiat;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/note")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteResource {

    @Inject
    NoteService noteService;

    @GET
    public Map<String, List<NoteEntity>> getNotes() {
        return noteService.findNotes();
    }

    @POST
    public Map<String, NoteEntity> postNote(NoteRequest note) {
        return noteService.insertNote(note.getTitle(), note.getContent());
    }

    @PUT
    public Map<String, NoteEntity> putNote(NoteRequest note) {
        return noteService.updateNote(note.getId(), note.getTitle(), note.getContent());
    }

    @DELETE
    public Map<String, String> deleteNote(NoteRequest note) {
        return noteService.deleteNote(note.getId());
    }

}
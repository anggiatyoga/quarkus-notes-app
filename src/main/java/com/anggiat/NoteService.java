package com.anggiat;

import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Transactional
public class NoteService {


	public Map<String, List<NoteEntity>> findNotes() {
		List<NoteEntity> notes = NoteEntity.findAll(Sort.by("updateAt").direction(Sort.Direction.Ascending)).list();
		return Map.of("data", notes);
	}

	public Map<String, NoteEntity> insertNote(String title, String content) {
		var note = new NoteEntity(title, content, OffsetDateTime.now());
		note.persistAndFlush();
		return Map.of("data", note);
	}

	public Map<String, NoteEntity> updateNote(Long id, String title, String content) {
		NoteEntity note = NoteEntity.findById(id);
		note.setTitle(title);
		note.setContent(content);
		note.setUpdateAt(OffsetDateTime.now());
		note.persistAndFlush();
		return Map.of("data", note);
	}

	public Map<String, String> deleteNote(Long id) {
		var note = NoteEntity.findById(id);
		note.delete();
		return Map.of("message", "success delete");
	}
}

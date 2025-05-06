class Notes {
  final String title;
  final String content;


  Notes({required this.title, required this.content});
}

List<Notes> _createNotes() {
  final notes = [
    Notes(
      title: "Note 1",
      content: "Contenu de la note 1",
    ),
    Notes(
      title: "Note 2",
      content: "Contenu de la note 2",
    ),
    Notes(
      title: "Note 3",
      content: "Contenu de la note 3",
    ),
  ];

  return notes;
}

final defaultNotes = _createNotes();
  
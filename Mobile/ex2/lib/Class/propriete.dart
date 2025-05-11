class Propriete {
  final String state; // "Vente" or "Location"
  final String type; // "Maison", "Appartement", etc.
  final String area; // "100 m²", "200 m²", etc.
  final String nbrChambre; // "3 chambres", "4 chambres", etc.
  final String price;


  Propriete({
    required this.state,
    required this.type,
    required this.area,
    required this.nbrChambre,
    required this.price,
  });
}

List<Propriete> _createProprietes() {
  final proprietes = [
    Propriete(
      state: "Vente",
      type: "Maison",
      area: "100 m²",
      nbrChambre: "3 chambres",
      price: "200000",
    ),
    Propriete(
      state: "Location",
      type: "Appartement",
      area: "80 m²",
      nbrChambre: "2 chambres",
      price: "1500",
    ),
    Propriete(
      state: "Vente",
      type: "Terrain",
      area: "500 m²",
      nbrChambre: "N/A",
      price: "100000",
    ),
  ];

  return proprietes;
}

final defaultProprietes = _createProprietes();

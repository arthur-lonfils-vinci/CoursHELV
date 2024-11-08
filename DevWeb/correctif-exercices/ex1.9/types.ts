interface Text {
  id: string;
  content: string;
  level: "easy" | "medium" | "hard";
}

type NewText = Omit<Text, "id">;

export type { Text, NewText };

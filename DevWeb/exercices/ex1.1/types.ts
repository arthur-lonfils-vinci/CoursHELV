interface film {
  id: number;
  title: string;
  director: string;
  duration: number;
  budget?: number;
  description?: URL;
  imageURL?: URL;
}


export type { film};

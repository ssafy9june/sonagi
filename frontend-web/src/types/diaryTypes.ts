interface DiaryPutDto {
  diaryId: number;
  content: string;
  removeFiles: string[];
}

interface DiaryPostDto {
  userId: number;
  babyId: number;
  content: string;
  writeTime: string;
  writeDate: string;
}

interface DiaryInfo {
  diaryId: number;
  userName: string;
  writedTime: string;
  imgUrls: string[];
  content: string;
}

export type { DiaryPutDto, DiaryPostDto, DiaryInfo };

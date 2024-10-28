import { DiaryInfo } from '@/types/diaryTypes';
import { atom } from 'recoil';

// 일기 기록 여부(date) 리스트 달력 체크용
export const writtenDiaryDateList = atom<string[]>({
  key: 'writtenDiaryDateList',
  default: [],
});

// 일기 기록 내용(DiaryInfo) 날짜에 해당하는 일기리스트.
export const diaryRecordList = atom<DiaryInfo[]>({
  key: 'diaryRecordList',
  default: [],
});

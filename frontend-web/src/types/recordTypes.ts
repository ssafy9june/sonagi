// category랑 color 일일이 다 지정해줘야할수도?

export const AllTypeValues = [
  'infantFormulas',
  'breastFeedings',
  'babyFoods',
  'milks',
  'pumpingBreasts',
  'pees',
  'poops',
  'hospitals',
  'medications',
  'snacks',
  'extras',
  'sleeps',
  'plays',
  'tummytimes',
  'feedings',
  'fevers',
];

export type RecordIdKeys =
  | 'mealId'
  | 'healthId'
  | 'pumpingBreastId'
  | 'diaperId'
  | 'healthId'
  | 'sleepId'
  | 'activityId'
  | 'extraId';

export type AllType = (typeof AllTypeValues)[number];

interface RecordCommon {
  id?: number;
  userId?: number;
  babyId?: number;
  createdDate?: string;
  createdTime?: string;
  memo?: string;
}
// 수유
interface Feeding extends RecordCommon {
  leftStartTime?: string;
  rightStartTime?: string;
  leftEndTime?: string;
  rightEndTime?: string;
  mealId?: number;
  // recordName: 'feedings';
}

// 체온
interface Fever extends RecordCommon {
  bodyTemperature: number;
  healthId?: number;
  // recordName: 'fevers';
}

interface InfantFormula extends RecordCommon {
  amount: number;
  mealId?: number;
  // recordName: 'infantFormulas';
}
interface BreastFeeding extends RecordCommon {
  amount: number;
  mealId?: number;
  // recordName: 'breastFeedings';
}
interface BabyFood extends RecordCommon {
  amount: number;
  mealId?: number;
  // recordName: 'babyFoods';
}

interface Milk extends RecordCommon {
  amount: number;
  mealId?: number;
  // recordName: 'milks';
}

interface PumpingBreast extends RecordCommon {
  amount: number;
  pumpingBreastId?: number;
  // recordName: 'pumpingBreasts';
}
// 유축 (개별 카테고리 있음)

interface Pee extends RecordCommon {
  diaperId?: number;
  // recordName: 'pees';
}
interface Poop extends RecordCommon {
  diaperId?: number;
  // recordName: 'poops';
}
interface Hospital extends RecordCommon {
  healthId?: number;
  // recordName: 'hospitals';
}
interface Medication extends RecordCommon {
  healthId?: number;
  // recordName: 'medications';
}
interface Snack extends RecordCommon {
  mealId?: number;
  // recordName: 'snacks';
}
interface Extra extends RecordCommon {
  extraId?: number;
  // recordName: 'extras';
}

interface Sleep extends RecordCommon {
  endTime: string;
  sleepId?: number;
  // recordName: 'sleeps';
}
interface Play extends RecordCommon {
  endTime: string;
  activityId?: number;
  // recordName: 'plays';
}
interface Tummytime extends RecordCommon {
  endTime: string;
  activityId?: number;
  // recordName: 'tummytimes';
}

type RecordedValues = {
  [key in AllType]: AllRecords[];
};

type AllRecords =
  | Play
  | Tummytime
  | Pee
  | Poop
  | Fever
  | Medication
  | Hospital
  | BabyFood
  | BreastFeeding
  | Feeding
  | InfantFormula
  | Milk
  | Snack
  | PumpingBreast
  | Sleep
  | Extra;

// 카테고리로 요청 보낼 수 있나?
type CombinedRecord = AllRecords & { category?: string };

export const categotyToRecordMap = {
  All: null,
  Meal: [
    'feedings',
    'infantFormulas',
    'breastFeedings',
    'babyFoods',
    'snacks',
    'milks',
  ],
  Diaper: ['pees', 'poops'],
  Sleep: ['sleeps'],
  Pump: ['pumpingBreasts'],
  Activity: ['plays', 'tummytimes'],
  Health: ['fevers', 'hospitals', 'medications'],
  Extra: ['extras'],
};

export type {
  AllRecords,
  Feeding,
  Fever,
  InfantFormula,
  BreastFeeding,
  BabyFood,
  Milk,
  PumpingBreast,
  Pee,
  Poop,
  Hospital,
  Medication,
  Snack,
  Extra,
  Sleep,
  Play,
  Tummytime,
  RecordedValues,
  CombinedRecord,
};

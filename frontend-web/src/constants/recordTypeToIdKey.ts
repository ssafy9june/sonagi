import { AllType, RecordIdKeys } from '@/types/recordTypes';

export const recordTypeToIdKey: { [key in AllType]: RecordIdKeys } = {
  infantFormulas: 'mealId',
  breastFeedings: 'mealId',
  babyFoods: 'mealId',
  milks: 'mealId',
  pumpingBreasts: 'pumpingBreastId',
  pees: 'diaperId',
  poops: 'diaperId',
  hospitals: 'healthId',
  medications: 'healthId',
  snacks: 'mealId',
  extras: 'extraId',
  sleeps: 'sleepId',
  plays: 'activityId',
  tummytimes: 'activityId',
  feedings: 'mealId',
  fevers: 'healthId',
};

import { DefaultTheme } from 'styled-components';

const color = {
  // text color
  white1: 'white',
  white2: 'rgba(255, 255, 255, 0.60)', // 버튼 중에 하양색 오퍼시티 60%
  black1: '#343434',
  black2: '#464646',
  black3: '#525252',
  black4: 'rgba(0,0,0,0.2)',
  black5: 'rgba(0,0,0,0.1)',
  gray1: '#999999',
  gray2: '#c3c3c3',
  gray3: '#d7d7d7',
  gray4: 'rgba(215, 215, 215, 0.50)',

  // main color
  mainblue: '#38A1ED',
  orange: '#ff8d24',

  // light grey for others
  lightgrey: '#F5F5F5',

  // button color
  skyblue: '#8cc8ff',
  primaryblue: '#49b3ff',
  blue: '#0094ff',
  blueOpacity10: '#0094ff10',
  green: '#89cb94',
  danger: '#FA6C4D',
  medicalblue: '#4E73F8',

  //category color
  categoryMeal: '#ff6c6c96',
  categoryDiaper: '#FF8D2496',
  categoryPumpingBreast: '#ffe76c96',
  categorySleep: '#6CB8FF96',
  categoryHealth: '#33e16f96',
  categoryActivity: '#e26cff96',
  categoryExtra: '#d7d7d796',

  // 기록 block color
  // blockMeal: '#FCBEC9',
  // blockDiaper: '#F8CFB6',
  // blockPumpingBreast: '#F8F2BF',
  // blockSleep: '#CAD5F7',
  // blockActivity: '#EFC9EC',
  // blockHealth: '#C8E8B9',
  // blockExtra: '#D1D7E1',

  // 그래프 - meal color
  graphInfantFormula: '#FF809E',
  graphBreastFeeding: '#FFb7C8',
  graphBabyFood: '#FFF1CC',
  graphMilk: '#D3DEFF',

  // 그래프 - diaper color
  graphPoop: '#ECBF95',
  graphPee: '#FFE4AF',

  // 그래프 - sleep color
  graphSleep: '#A0D8F8',

  // 그래프 - health color
  graphFeverAverage: '#58EEA6',
  graphHospital: '#BFF37C',
  graphMedication: '#97CDFF',

  // 그래프 - activitie color
  graphPlayTime: '#EDBBFA',
  graphTummyTime: '#C8BBFA',

  // 그래프 - pumping breast color
  graphAmountsOfPumpingBreast: '#FFE6A6',
  graphCountsOfPumpingBreast: '#FFD056',

  // 그래프 - etc color
  graphExtra: '#E1E1E1',

  // 통계카드
  cardMeal1: '#EC7F7F',
  cardMeal2: '#F4A0A0',
  cardMeal3: '#FAB8B8',

  cardDiaper1: '#FFA552',
  cardDiaper2: '#FFD6A6',

  cardHealth1: '#97DA7B',
  cardHealth2: '#BFF37C',
  cardHealth3: '#CAF09A',

  cardSleep1: '#6CB8FF',
  cardSleep2: '#A0D8F8',

  cardActivity1: '#DABBFA',
  cardActivity2: '#EDCCF6',

  cardPumpingBreast1: '#FFD056',
  cardPumpingBreast2: '#FFE6A6',

  cardExtra: '#BDBDBD',
  // 일자 달력 색깔
  calendarTodayBg: '#E9FAFB',
  calendarTodayFont: '#4EB1F8',
  calendarBeforeFont: '#94A3B8',
  calendarDot: '#92D1FF',
} as const;

const fontSize = {
  headXLarge: '40px',
  headLarge: '30px',
  headMedium: '25px',
  headSmall: '18px',
  large: '20px',
  medium1: '16px',
  medium2: '15px',
  medium3: '13px',
  small: '11px',
  xSmall: '8px',
} as const;

const letterSpacing = {
  narrow: '-2.4px',
  spread: '0.4px',
} as const;

const shadow = {
  // shadow
  shadow1: '0px 8px 21px 0px rgba(0, 0, 0, 0.16)', //buttons에 추가되는 기본 shadow
};

const gradient = {
  // linear gradient
  orangeBtn:
    'linear-gradient(134deg, rgba(255, 234, 123, 0.80) 0%, rgba(255, 141, 36, 0.80) 100%)',
  skyblueBtn: 'linear-gradient(134deg, #CAE3FF 0%, #38A1ED 100%)',
};

const theme: DefaultTheme = {
  color,
  fontSize,
  letterSpacing,
  shadow,
  gradient,
};

export default theme;

/// <reference types="react-scripts" />

declare namespace NodeJS {
  interface ProcessEnv {
    NODE_ENV: 'development' | 'production' | 'test';
    REACT_APP_SERVER_URL: string;
    REsACT_APP_BASE_URL: string;
    REACT_APP_KAKAO_KEY: number;
  }
}

interface Window {
  Kakao: any;
}

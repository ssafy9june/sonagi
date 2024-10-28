import { Suspense } from 'react';
import LogInPage from '@/pages/LogInPage/LogInPage';
import LoadingPage from '@/pages/LoadingPage/LoadingPage';

function App() {
  return (
    <Suspense fallback={<LoadingPage></LoadingPage>}>
      <>
        <LogInPage />
      </>
    </Suspense>
  );
}

export default App;

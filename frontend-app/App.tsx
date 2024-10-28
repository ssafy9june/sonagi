import React, {useCallback, useRef, useState} from 'react';
import WebView from 'react-native-webview';
import messaging from '@react-native-firebase/messaging';
import {useEffect} from 'react';
import {Alert, BackHandler} from 'react-native';
import pushNoti from './android/app/src/utils/pushNoti';
import Clipboard from '@react-native-clipboard/clipboard';
import {PermissionsAndroid} from 'react-native';
import Toast from 'react-native-toast-message';

const App = () => {
  // TODO: 빌드할때 fetch부분 링크 잊지말기

  // 뒤로가기 로직
  const webViewRef = useRef<any>(null);
  const [isCanGoBack, setIsCanGoBack] = useState(false);
  const [fcmTokenState, setFcmTokenState] = useState('');
  const onPressHardwareBackButton = useCallback(() => {
    if (webViewRef.current && isCanGoBack) {
      webViewRef.current.goBack();
      return true;
    } else {
      return false;
    }
  }, [isCanGoBack]);
  useEffect(() => {
    const unsubscribe = messaging().onMessage(async remoteMessage => {
      console.log(remoteMessage);
      pushNoti.displayNoti(remoteMessage);
    });
    return unsubscribe;
  }, []);

  useEffect(() => {
    BackHandler.addEventListener(
      'hardwareBackPress',
      onPressHardwareBackButton,
    );
    return () => {
      BackHandler.removeEventListener(
        'hardwareBackPress',
        onPressHardwareBackButton,
      );
    };
  }, [onPressHardwareBackButton]);

  // 상단 토스트
  const showToast = () => {
    Toast.show({
      type: 'success',
      text1: '권한 설정 확인',
      text2: '권한이 정상적으로 설정되어 있습니다 👋',
    });
    console.log('toastshow');
  };

  // 권한 설정에 문제 있는 경우 표시되는 Alert창
  const recheckPermissions = () => {
    Alert.alert(
      '권한 설정에 문제가 발생하였어요.',
      '권한 설정을 다시 시도해주세요',
      [
        {
          text: '재시도',
          onPress: () => {
            PermissionsAndroid.requestMultiple([
              PermissionsAndroid.PERMISSIONS.POST_NOTIFICATIONS,
              PermissionsAndroid.PERMISSIONS.READ_MEDIA_IMAGES,
            ]);
          },
          style: 'default',
        },
        {
          text: '나중에 하기',
          onPress: () => {},
          style: 'cancel',
        },
      ],
      {
        cancelable: true,
        onDismiss: () => {},
      },
    );
  };

  // 권한 설정이 불가능한 경우 표시되는 Alert차
  const neverPermissions = () => {
    Alert.alert(
      '권한 설정에 문제가 발생하였어요.',
      '설정에서 직접 권한을 설정하시거나 앱을 재설치 해주세요',
      [
        {
          text: '확인',
          onPress: () => {},
          style: 'cancel',
        },
      ],
      {
        cancelable: true,
        onDismiss: () => {},
      },
    );
  };

  useEffect(() => {
    requestUserPermission();
    requestPermission();
  });

  // PermissionsAndroid 사용 로직

  const requestPermission = () => {
    try {
      PermissionsAndroid.requestMultiple([
        PermissionsAndroid.PERMISSIONS.POST_NOTIFICATIONS,
        PermissionsAndroid.PERMISSIONS.READ_MEDIA_IMAGES,
      ]).then(result => {
        if (
          result['android.permission.POST_NOTIFICATIONS'] === 'granted' &&
          result['android.permission.READ_MEDIA_IMAGES'] === 'granted'
        ) {
          console.log('모든 권한 획득', result);
          showToast();
        } else if (
          result['android.permission.POST_NOTIFICATIONS'] === 'denied' ||
          result['android.permission.READ_MEDIA_IMAGES'] === 'denied'
        ) {
          console.log('거절된 권한있음', result);
          recheckPermissions();
        } else {
          console.log('never_ask_again', result);
          neverPermissions();
        }
      });
    } catch (err) {
      console.warn(err);
    }
  };

  // 토큰 값 가져오기 관련 로직
  const requestUserPermission = async () => {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;

    if (enabled) {
      return getToken();
    }
  };

  const getToken = async () => {
    const fcmToken = await messaging().getToken();

    try {
      console.log(fcmToken);
      setFcmTokenState(fcmToken);
      webViewRef.current.postMessage(fcmToken);
    } catch (e) {
      console.log(e, 'Error');
    }
  };

  // React에서 userId받아서 /api/fcm으로 보내기 로직
  const userIdHandler = async (receivedMessage: any) => {
    const requestBody = {
      userId: receivedMessage.code,
      firebaseToken: fcmTokenState,
    };
    try {
      const response = await fetch('http://localhost:8080/api/fcm', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
      });

      if (response.ok) {
        console.log(requestBody.firebaseToken);
        console.log('API request successful');
      } else {
        console.error('API request failed with status', response.status);
      }
    } catch (error) {
      console.log('fetch error', error);
    }
  };

  return (
    <>
      <WebView
        ref={webViewRef}
        source={{
          uri: 'http://localhost:3000',
        }}
        onLoad={() => {}}
        // userAgent="Mozilla/5.0 (Linux; Android 13; SAMSUNG SM-S906U) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/22.0 Chrome/111.0.5563.116 Mobile Safari/537.36 kwdApp"
        userAgent="kwdApp"
        sharedCookiesEnabled={true}
        domStorageEnabled={true}
        allowFileAccess={true}
        javaScriptEnabledAndroid={true}
        injectedJavaScript={`
      (function() {
        function wrap(fn) {
          return function wrapper() {
            var res = fn.apply(this, arguments);
            window.ReactNativeWebView.postMessage
            (
              JSON.stringify({
                type: 'navigationStateChange',
                code: 'navigationStateChange',
              })
              );
              return res;
            }
          }
          
          history.pushState = wrap(history.pushState);
          history.replaceState = wrap(history.replaceState);
          window.addEventListener('popstate', function() {
            window.ReactNativeWebView.postMessage(
              JSON.stringify({
                type: 'navigationStateChange'
              })
              );
            });
          })();
          
          true;
          `}
        onMessage={({nativeEvent: state}) => {
          try {
            const receivedMessage = JSON.parse(state.data);
            if (receivedMessage.type === 'navigationStateChange') {
              setIsCanGoBack(state.canGoBack);
            } else if (receivedMessage.type === 'BabyCode') {
              webViewRef.current.requestFocus();
              Clipboard.setString(receivedMessage.code);
              console.log('clipboard success', receivedMessage.code);
            } else if (receivedMessage.type === 'userId') {
              console.log(receivedMessage.code);
              userIdHandler(receivedMessage);
            }
          } catch (error) {
            console.log(error);
          }
        }}
      />
      <Toast />
    </>
  );
};

export default App;

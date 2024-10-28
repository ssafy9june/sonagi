import notifee, {AndroidImportance} from '@notifee/react-native';

const displayNotification = async (message: any) => {
  const channelAnoucement = await notifee.createChannel({
    id: 'default',
    name: 'Sonagi',
    importance: AndroidImportance.HIGH,
  });

  await notifee.displayNotification({
    title: message.data.title,
    body: message.data.body,
    android: {
      channelId: channelAnoucement,
      smallIcon: 'ic_notification',
      color: '#6CB8FF',
    },
  });
};

export default {
  displayNoti: (remoteMessage: any) => displayNotification(remoteMessage),
};

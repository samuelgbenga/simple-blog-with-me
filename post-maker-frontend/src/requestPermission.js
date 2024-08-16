
import { getToken, onMessage } from "firebase/messaging";
import { saveToken } from "./api/saveToken";
import { messaging } from "./firebase";




export const requestPermission = async () => {
  try {
    // Check if the browser supports Service Workers and Push Notifications
    if ("serviceWorker" in navigator && "PushManager" in window) {
      // Request Notification permission
      const permission = await Notification.requestPermission();
      if (permission === "granted") {
        // Get FCM token
        const token = await getToken(messaging, {
          vapidKey: import.meta.env.VITE_APP_VAPID_KEY,
        });

        if (token) {
          console.log("Token:", token);

          const pushNotification = {
            token: token,
          };

          const response = await saveToken(pushNotification, {});
          if (response) {
            console.log("Token saved status:", response);
          }
        } else {
          console.error("Failed to get the registration token.");
        }
      } else {
        console.error("Permission not granted for Notification");
      }
    } else {
      console.warn("Push messaging is not supported by this browser");
    }
  } catch (error) {
    console.error("Error:", error);
  }
};

// Optional: Handle incoming messages while the app is in the foreground
onMessage(messaging, (payload) => {
  console.log("Message received. ", payload);
  // Customize your notification handling here if needed
});

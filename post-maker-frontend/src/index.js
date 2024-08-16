// src/index.js
if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    //const swUrl = `${import.meta.env.BASE_URL}/service-worker.js`;

    navigator.serviceWorker
      .register("/service-worker.js")
      .then((registration) => {
        console.log(
          "Service Worker registered with scope:",
          registration.scope
        );
      })
      .catch((error) => {
        console.error("Service Worker registration failed:", error);
      });
  });
}

import React, { useState, useRef } from "react";
import { registerUser } from "../api/registerUser";
import { Navigate } from "react-router-dom";

const RegistrationPage = () => {
  const [regResponse, setRegResponse] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const fullNameRef = useRef();
  const usernameRef = useRef();
  const emailRef = useRef();
  const phoneNumberRef = useRef();
  const passwordRef = useRef();

  const handleOnSubmit = async (e) => {
    e.preventDefault();

    const userInfo = {
      fullName: fullNameRef.current.value,
      username: usernameRef.current.value,
      email: emailRef.current.value,
      phoneNumber: phoneNumberRef.current.value,
      password: passwordRef.current.value,
    };

    setIsLoading(true);
    try {
      const response = await registerUser(userInfo, {});
      setRegResponse(response);
    } catch (error) {
      //console.error("Error registering user:", error);
    } finally {
      setIsLoading(false);
    }
  };

  if (regResponse?.responseCode === "001") {
    return <Navigate to="/" />;
  }

  return (
    <div style={styles.outerContainer}>
      <div style={styles.container}>
        <form onSubmit={handleOnSubmit} style={styles.form}>
          <label style={styles.label}>
            Fullname
            <input type="text" ref={fullNameRef} style={styles.input} />
          </label>
          <label style={styles.label}>
            Username
            <input type="text" ref={usernameRef} style={styles.input} />
          </label>
          <label style={styles.label}>
            Email
            <input type="email" ref={emailRef} style={styles.input} />
          </label>
          <label style={styles.label}>
            Phone Number
            <input type="text" ref={phoneNumberRef} style={styles.input} />
          </label>
          <label style={styles.label}>
            Password
            <input type="password" ref={passwordRef} style={styles.input} />
          </label>
          <button type="submit" style={styles.button}>
            Register
          </button>
        </form>
        {isLoading && <div className="loader" style={styles.loader}>Processing...</div>}
      </div>
    </div>
  );
};

const styles = {
  outerContainer: {
    width: "100vw",
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },
  container: {
    maxWidth: "400px",
    padding: "20px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.1)",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  label: {
    marginBottom: "10px",
    fontWeight: "bold",
  },
  input: {
    padding: "8px",
    fontSize: "16px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    marginBottom: "15px",
  },
  button: {
    padding: "10px",
    fontSize: "16px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  loader: {
    marginTop: "15px",
    fontSize: "16px",
    color: "#007bff",
    textAlign: "center",
  },
};

export default RegistrationPage;

import React, { useEffect, useRef, useState } from "react";
import { Link, Navigate } from "react-router-dom";
import { createPost } from "../api/createPost";

const NewPostPage = () => {
  const [username, setUsername] = useState(
    localStorage.getItem("username") || null
  );
  const [isLoading, setIsLoading] = useState(false);
  const [createPostResp, setCreatePostResp] = useState(null);

  const titleRef = useRef();
  const postRef = useRef();

  const handleOnSubmit = async (e) => {
    e.preventDefault();

    const post = {
      postTitle: titleRef.current.value,
      post: postRef.current.value,
    };

    setIsLoading(true);
    try {
      const response = await createPost(username, post, {});
      setCreatePostResp(response);
    } catch (error) {
      console.error("Error creating post:", error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    console.log(createPostResp);
  }, [createPostResp]);

  if (!username) {
    return <Navigate to="/" />;
  }

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Create a New Post</h2>
      <Link to={`/${username}`} style={styles.link}>Back to Page</Link>
      <form onSubmit={handleOnSubmit} style={styles.form}>
        <label style={styles.label}>
          Title:
          <input type="text" ref={titleRef} style={styles.input} />
        </label>

        <label style={styles.label}>
          Post:
          <textarea ref={postRef} style={styles.textarea} />
        </label>
        <button type="submit" style={styles.button}>
          Submit
        </button>
      </form>
      {isLoading && <div style={styles.loader}>Processing...</div>}
    </div>
  );
};

const styles = {
  container: {
    width: "80%",
    margin: "auto",
    padding: "20px",
    borderRadius: "8px",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.1)",
    backgroundColor: "#f9f9f9",
    textAlign: "center",
  },
  heading: {
    marginBottom: "20px",
    fontSize: "24px",
    fontWeight: "bold",
    color: "#333",
  },
  link: {
    display: "block",
    marginBottom: "20px",
    color: "#007bff",
    textDecoration: "none",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  label: {
    marginBottom: "10px",
    width: "100%",
    fontWeight: "bold",
    textAlign: "left",
  },
  input: {
    width: "100%",
    padding: "8px",
    fontSize: "16px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    marginBottom: "15px",
  },
  textarea: {
    width: "100%",
    height: "100px",
    padding: "8px",
    fontSize: "16px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    marginBottom: "15px",
  },
  button: {
    padding: "10px 20px",
    fontSize: "16px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  loader: {
    marginTop: "20px",
    fontSize: "16px",
    color: "#007bff",
  },
};

export default NewPostPage;

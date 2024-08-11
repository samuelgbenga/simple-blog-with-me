import React, { useEffect, useState } from "react";
import { Navigate, Link, useLoaderData } from "react-router-dom";
import { getPosts } from "../api/getPosts";

const LandingPage = () => {
  const [username, setUsername] = useState(localStorage.getItem("username"));

  // Get posts from useLoaderData
  const { posts } = useLoaderData();

  const handleLogout = () => {
    localStorage.removeItem("username");
    setUsername(null);
  };

  useEffect(() => {
    console.log(posts);
  }, [posts]);

  // Conditionally render Navigate if username is not set
  if (!username) {
    return <Navigate to="/" />;
  }

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h1>LandingPage</h1>
        <button onClick={handleLogout} style={styles.logoutButton}>Logout</button>
      </div>
      <div style={styles.postsContainer}>
        {posts.map((post, index) => (
          <div key={index} style={styles.post}>
            <h2 style={styles.postTitle}>{post.postTitle}</h2>
            <p style={styles.postContent}>{post.post}</p>
          </div>
        ))}
      </div>
      <Link to="/new-post" style={styles.newPostLink}>Create new Post</Link>
    </div>
  );
};

const styles = {
  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "flex-end",
    padding: "20px",
    width: "90%",
  },
  header: {
    width: "70%",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "20px",
  },
  logoutButton: {
    padding: "10px 20px",
    fontSize: "16px",
    backgroundColor: "#ff4d4d",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  postsContainer: {
    width: "70%",
  },
  post: {
    marginBottom: "20px",
    padding: "20px",
    border: "1px solid #ddd",
    borderRadius: "8px",
    backgroundColor: "#f9f9f9",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.1)",
  },
  postTitle: {
    marginBottom: "10px",
    fontSize: "20px",
    fontWeight: "bold",
  },
  postContent: {
    fontSize: "16px",
    color: "#555",
  },
  newPostLink: {
    marginTop: "20px",
    textDecoration: "none",
    color: "#007bff",
    fontSize: "16px",
    alignSelf: "flex-end",
  },
};

async function loader({ request: { signal } }) {
  // Fetch the posts using the signal and return them
  const posts = await getPosts(localStorage.getItem("username"), { signal });

  return { posts };
}

export const landingPage = {
  loader,
  element: <LandingPage />,
};

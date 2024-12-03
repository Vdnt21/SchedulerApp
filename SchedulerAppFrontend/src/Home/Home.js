import React, { useState } from "react";
import axios from "axios";

function HomePageComponent() {
  const [formData, setFormData] = useState({
    clientName: "",
    apiUrl: "",
    interval: "",
    payload: "",
    headers: "",
  });

  const intervals = [
    { label: "Every 5 minutes", value: "*/5 * * * *" },
    { label: "Every 10 minutes", value: "*/10 * * * *" },
    { label: "Every 15 minutes", value: "*/15 * * * *" },
    { label: "Every 30 minutes", value: "*/30 * * * *" },
    { label: "Every hour", value: "0 * * * *" },
    { label: "Every 6 hours", value: "0 */6 * * *" },
    { label: "Every 12 hours", value: "0 */12 * * *" },
    { label: "Daily", value: "0 0 * * *" },
    { label: "Weekly", value: "0 0 * * 0" },
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("/api/schedules", formData);
      alert("Schedule created successfully!");
      console.log(response.data);
    } catch (error) {
      console.error("Error creating schedule:", error);
      alert("Failed to create schedule.");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Schedule an API Call</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="clientName" className="form-label">Client Name</label>
          <input
            type="text"
            className="form-control"
            id="clientName"
            name="clientName"
            value={formData.clientName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="apiUrl" className="form-label">API URL</label>
          <input
            type="url"
            className="form-control"
            id="apiUrl"
            name="apiUrl"
            value={formData.apiUrl}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="interval" className="form-label">Interval</label>
          <select
            className="form-select"
            id="interval"
            name="interval"
            value={formData.interval}
            onChange={handleChange}
            required
          >
            <option value="">Select an interval</option>
            {intervals.map((int) => (
              <option key={int.value} value={int.value}>
                {int.label}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="payload" className="form-label">Payload (JSON)</label>
          <textarea
            className="form-control"
            id="payload"
            name="payload"
            rows="3"
            value={formData.payload}
            onChange={handleChange}
          ></textarea>
        </div>
        <div className="mb-3">
          <label htmlFor="headers" className="form-label">Headers (JSON)</label>
          <textarea
            className="form-control"
            id="headers"
            name="headers"
            rows="2"
            value={formData.headers}
            onChange={handleChange}
          ></textarea>
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  );
}

export default HomePageComponent;

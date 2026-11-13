
import { useState } from "react";
import {
  LayoutDashboard,
  Users,
  GraduationCap,
  BookOpen,
  ChartNoAxesCombined,
  Settings,
  Search,
  Bell,
  Plus,
  MoreVertical,
  Pencil,
  Trash2,
  Mail,
  Menu,
  X,
  CalendarDays,
  ArrowUpRight,
  UserRound
} from "lucide-react";

import "./App.css";

const initialStudents = [
  { id: 1, name: "Arun Kumar", email: "arun@gmail.com", department: "Computer Science", year: "III Year", status: "Active" },
  { id: 2, name: "Priya Sharma", email: "priya@gmail.com", department: "Information Technology", year: "II Year", status: "Active" },
  { id: 3, name: "Rahul Kumar", email: "rahul@gmail.com", department: "Computer Science", year: "IV Year", status: "Active" },
  { id: 4, name: "Sneha R", email: "sneha@gmail.com", department: "Electronics", year: "I Year", status: "Inactive" },
  { id: 5, name: "Vikram S", email: "vikram@gmail.com", department: "Computer Science", year: "III Year", status: "Active" },
];

function App() {
  const [students, setStudents] = useState(initialStudents);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [editingStudent, setEditingStudent] = useState(null);
  const [mobileMenu, setMobileMenu] = useState(false);
  const [activePage, setActivePage] = useState("Dashboard");

  const [form, setForm] = useState({
    name: "",
    email: "",
    department: "Computer Science",
    year: "I Year",
  });

  const filteredStudents = students.filter((student) =>
    `${student.name} ${student.email} ${student.department}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  const activeCount = students.filter(s => s.status === "Active").length;

  function openAddModal() {
    setEditingStudent(null);
    setForm({
      name: "",
      email: "",
      department: "Computer Science",
      year: "I Year",
    });
    setShowModal(true);
  }

  function openEditModal(student) {
    setEditingStudent(student);
    setForm({
      name: student.name,
      email: student.email,
      department: student.department,
      year: student.year,
    });
    setShowModal(true);
  }

  function saveStudent(e) {
    e.preventDefault();

    if (editingStudent) {
      setStudents(students.map(s =>
        s.id === editingStudent.id
          ? { ...s, ...form }
          : s
      ));
    } else {
      setStudents([
        ...students,
        {
          ...form,
          id: Date.now(),
          status: "Active",
        },
      ]);
    }

    setShowModal(false);
  }

  function deleteStudent(id) {
    if (window.confirm("Are you sure you want to delete this student?")) {
      setStudents(students.filter(s => s.id !== id));
    }
  }

  const menuItems = [
    { name: "Dashboard", icon: LayoutDashboard },
    { name: "Students", icon: Users },
    { name: "Courses", icon: BookOpen },
    { name: "Departments", icon: GraduationCap },
    { name: "Reports", icon: ChartNoAxesCombined },
    { name: "Settings", icon: Settings },
  ];

  return (
    <div className="app-layout">

      <aside className={`sidebar ${mobileMenu ? "sidebar-open" : ""}`}>
        <div className="brand">
          <div className="brand-icon"><GraduationCap size={25} /></div>
          <span>EduManage</span>
          <button className="close-menu" onClick={() => setMobileMenu(false)}>
            <X size={20} />
          </button>
        </div>

        <div className="sidebar-label">MAIN MENU</div>

        <nav>
          {menuItems.map((item) => {
            const Icon = item.icon;
            return (
              <button
                key={item.name}
                className={`nav-item ${activePage === item.name ? "active" : ""}`}
                onClick={() => {
                  setActivePage(item.name);
                  setMobileMenu(false);
                }}
              >
                <Icon size={19} />
                <span>{item.name}</span>
              </button>
            );
          })}
        </nav>

        <div className="sidebar-bottom">
          <div className="help-card">
            <div className="help-icon">?</div>
            <strong>Need help?</strong>
            <p>Contact our support team for assistance.</p>
            <button onClick={() => alert("Contact: support@edumanage.com")}>
              Get Support
            </button>
          </div>

          <div className="profile">
            <div className="avatar">JA</div>
            <div className="profile-info">
              <strong>Admin User</strong>
              <span>Administrator</span>
            </div>
            <MoreVertical size={19} />
          </div>
        </div>
      </aside>

      {mobileMenu && (
        <div className="mobile-overlay" onClick={() => setMobileMenu(false)} />
      )}

      <main className="main-content">

        <header className="topbar">
          <button className="menu-toggle" onClick={() => setMobileMenu(true)}>
            <Menu size={23} />
          </button>

          <div className="breadcrumb">
            <span>Pages</span>
            <span>/</span>
            <strong>{activePage}</strong>
          </div>

          <div className="topbar-right">
            <div className="top-search">
              <Search size={18} />
              <input
                placeholder="Search anything..."
                value={search}
                onChange={(e) => {
                  setSearch(e.target.value);
                  setActivePage("Students");
                }}
              />
            </div>
            <button className="icon-button" aria-label="Notifications">
              <Bell size={20} />
              <span className="notification-dot"></span>
            </button>
            <div className="top-avatar">JA</div>
          </div>
        </header>

        <section className="page-content">

          <div className="welcome-section">
            <div>
              <div className="date-label">
                <CalendarDays size={15} /> STUDENT MANAGEMENT SYSTEM
              </div>
              <h1>{activePage === "Dashboard" ? "Dashboard Overview" : activePage}</h1>
              <p>
                {activePage === "Dashboard"
                  ? "Welcome back! Here's what's happening in your institution."
                  : `Manage your institution's ${activePage.toLowerCase()} records.`}
              </p>
            </div>

            <button className="primary-button" onClick={openAddModal}>
              <Plus size={19} /> Add Student
            </button>
          </div>

          {activePage === "Dashboard" || activePage === "Students" ? (
            <>
              <div className="stats-grid">

                <div className="stat-card">
                  <div className="stat-top">
                    <div className="stat-icon purple"><Users size={22} /></div>
                    <span className="stat-change"><ArrowUpRight size={15} /> 12.5%</span>
                  </div>
                  <p>Total Students</p>
                  <h2>{students.length.toLocaleString()}</h2>
                  <span className="stat-note">Registered student records</span>
                </div>

                <div className="stat-card">
                  <div className="stat-top">
                    <div className="stat-icon green"><GraduationCap size={22} /></div>
                    <span className="stat-change"><ArrowUpRight size={15} /> 8.2%</span>
                  </div>
                  <p>Active Students</p>
                  <h2>{activeCount.toLocaleString()}</h2>
                  <span className="stat-note">Currently active students</span>
                </div>

                <div className="stat-card">
                  <div className="stat-top">
                    <div className="stat-icon orange"><BookOpen size={22} /></div>
                    <span className="stat-change">Academic</span>
                  </div>
                  <p>Departments</p>
                  <h2>{new Set(students.map(s => s.department)).size}</h2>
                  <span className="stat-note">Departments represented</span>
                </div>

                <div className="stat-card">
                  <div className="stat-top">
                    <div className="stat-icon blue"><ChartNoAxesCombined size={22} /></div>
                    <span className="stat-change">Updated</span>
                  </div>
                  <p>Inactive Students</p>
                  <h2>{students.length - activeCount}</h2>
                  <span className="stat-note">Currently inactive students</span>
                </div>

              </div>

              {activePage === "Dashboard" && (
                <div className="overview-grid">
                  <div className="panel enrollment-panel">
                    <div className="panel-heading">
                      <div>
                        <h3>Student Enrollment</h3>
                        <p>Student distribution by academic year</p>
                      </div>
                      <span className="panel-tag">Overview</span>
                    </div>

                    <div className="enrollment-chart">
                      {["I Year", "II Year", "III Year", "IV Year"].map((year, i) => {
                        const count = students.filter(s => s.year === year).length;
                        const percent = students.length ? (count / students.length) * 100 : 0;
                        return (
                          <div className="chart-row" key={year}>
                            <span>{year}</span>
                            <div className="chart-track">
                              <div
                                className="chart-fill"
                                style={{ width: `${percent}%` }}
                              ></div>
                            </div>
                            <strong>{count}</strong>
                          </div>
                        );
                      })}
                    </div>
                  </div>

                  <div className="panel quick-panel">
                    <div className="panel-heading">
                      <div>
                        <h3>Quick Actions</h3>
                        <p>Manage your institution</p>
                      </div>
                    </div>

                    <button className="quick-action" onClick={openAddModal}>
                      <div className="quick-icon purple"><Plus size={19} /></div>
                      <span><strong>Add Student</strong><small>Register a new student</small></span>
                      <ArrowUpRight size={17} />
                    </button>

                    <button className="quick-action" onClick={() => setActivePage("Students")}>
                      <div className="quick-icon green"><Users size={19} /></div>
                      <span><strong>View Students</strong><small>Manage student records</small></span>
                      <ArrowUpRight size={17} />
                    </button>

                    <button className="quick-action" onClick={() => setActivePage("Reports")}>
                      <div className="quick-icon orange"><ChartNoAxesCombined size={19} /></div>
                      <span><strong>View Reports</strong><small>Check student statistics</small></span>
                      <ArrowUpRight size={17} />
                    </button>
                  </div>
                </div>
              )}

              <div className="panel students-panel">
                <div className="panel-heading students-heading">
                  <div>
                    <h3>{activePage === "Dashboard" ? "Recent Students" : "All Students"}</h3>
                    <p>Manage and monitor student information</p>
                  </div>
                  <span className="student-count">{filteredStudents.length} Students</span>
                </div>

                <div className="table-wrapper">
                  <table>
                    <thead>
                      <tr>
                        <th>STUDENT</th>
                        <th>DEPARTMENT</th>
                        <th>ACADEMIC YEAR</th>
                        <th>STATUS</th>
                        <th>ACTIONS</th>
                      </tr>
                    </thead>
                    <tbody>
                      {filteredStudents.length > 0 ? filteredStudents.map((student) => (
                        <tr key={student.id}>
                          <td>
                            <div className="student-cell">
                              <div className="student-avatar">
                                {student.name.charAt(0).toUpperCase()}
                              </div>
                              <div>
                                <strong>{student.name}</strong>
                                <span><Mail size={13} /> {student.email}</span>
                              </div>
                            </div>
                          </td>
                          <td>{student.department}</td>
                          <td>{student.year}</td>
                          <td>
                            <span className={`status ${student.status.toLowerCase()}`}>
                              {student.status}
                            </span>
                          </td>
                          <td>
                            <div className="action-buttons">
                              <button title="Edit" onClick={() => openEditModal(student)}>
                                <Pencil size={16} />
                              </button>
                              <button title="Delete" className="delete-button" onClick={() => deleteStudent(student.id)}>
                                <Trash2 size={16} />
                              </button>
                            </div>
                          </td>
                        </tr>
                      )) : (
                        <tr>
                          <td colSpan="5" className="empty-state">
                            No students found. Try another search.
                          </td>
                        </tr>
                      )}
                    </tbody>
                  </table>
                </div>
              </div>
            </>
          ) : (
            <div className="panel placeholder-panel">
              <div className="stat-icon purple"><UserRound size={28} /></div>
              <h3>{activePage}</h3>
              <p>This section is ready for the next development phase.</p>
              {activePage === "Reports" && (
                <p>Total students: {students.length} · Active: {activeCount}</p>
              )}
              <button className="primary-button" onClick={() => setActivePage("Dashboard")}>
                Back to Dashboard
              </button>
            </div>
          )}

        </section>
      </main>

      {showModal && (
        <div className="modal-overlay">
          <div className="modal">
            <div className="modal-heading">
              <div>
                <h2>{editingStudent ? "Edit Student" : "Add New Student"}</h2>
                <p>Enter the student details below.</p>
              </div>
              <button className="close-button" onClick={() => setShowModal(false)}>
                <X size={21} />
              </button>
            </div>

            <form onSubmit={saveStudent}>
              <label>Full Name</label>
              <input
                required
                placeholder="Enter student name"
                value={form.name}
                onChange={(e) => setForm({ ...form, name: e.target.value })}
              />

              <label>Email Address</label>
              <input
                required
                type="email"
                placeholder="Enter email address"
                value={form.email}
                onChange={(e) => setForm({ ...form, email: e.target.value })}
              />

              <label>Department</label>
              <select
                value={form.department}
                onChange={(e) => setForm({ ...form, department: e.target.value })}
              >
                <option>Computer Science</option>
                <option>Information Technology</option>
                <option>Electronics</option>
                <option>Mechanical</option>
                <option>Civil</option>
              </select>

              <label>Academic Year</label>
              <select
                value={form.year}
                onChange={(e) => setForm({ ...form, year: e.target.value })}
              >
                <option>I Year</option>
                <option>II Year</option>
                <option>III Year</option>
                <option>IV Year</option>
              </select>

              <div className="modal-actions">
                <button type="button" className="cancel-button" onClick={() => setShowModal(false)}>
                  Cancel
                </button>
                <button type="submit" className="primary-button">
                  {editingStudent ? "Save Changes" : "Add Student"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

    </div>
  );
}

export default App;

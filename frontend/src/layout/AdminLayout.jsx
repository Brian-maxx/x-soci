import { BrowserRouter as Router, Routes, Route } from "react-router";
import SignIn from ".../pages/admin/admin/AuthPages/SignIn";
import SignUp from "../pages/admin/AuthPages/SignUp";
import NotFound from "../pages/admin/OtherPage/NotFound";
import UserProfiles from "../pages/admin/UserProfiles";
import Videos from "../pages/admin/UiElements/Videos";
import Images from "../pages/admin/UiElements/Images";
import Alerts from "../pages/admin/UiElements/Alerts";
import Badges from "../pages/admin/UiElements/Badges";
import Avatars from "../pages/admin/UiElements/Avatars";
import Buttons from "../pages/admin/UiElements/Buttons";
import LineChart from "../pages/admin/Charts/LineChart";
import BarChart from "../pages/admin/Charts/BarChart";
import Calendar from "../pages/admin/Calendar";
import BasicTables from "../pages/admin/Tables/BasicTables";
import FormElements from "../pages/admin/Forms/FormElements";
import Blank from "../pages/admin/Blank";
import AppLayout from "./admin/AppLayout";
import { ScrollToTop } from "../components/admin/common/ScrollToTop";
import Home from "../pages/admin/Dashboard/Home";

export default function App() {
  return (
    <>
      <Router>
        <ScrollToTop />
        <Routes>
          {/* Dashboard Layout */}
          <Route element={<AppLayout />}>
            <Route index path="/" element={<Home />} />

            {/* Others Page */}
            <Route path="/profile" element={<UserProfiles />} />
            <Route path="/calendar" element={<Calendar />} />
            <Route path="/blank" element={<Blank />} />

            {/* Forms */}
            <Route path="/form-elements" element={<FormElements />} />

            {/* Tables */}
            <Route path="/basic-tables" element={<BasicTables />} />

            {/* Ui Elements */}
            <Route path="/alerts" element={<Alerts />} />
            <Route path="/avatars" element={<Avatars />} />
            <Route path="/badge" element={<Badges />} />
            <Route path="/buttons" element={<Buttons />} />
            <Route path="/images" element={<Images />} />
            <Route path="/videos" element={<Videos />} />

            {/* Charts */}
            <Route path="/line-chart" element={<LineChart />} />
            <Route path="/bar-chart" element={<BarChart />} />
          </Route>

          {/* Auth Layout */}
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />

          {/* Fallback Route */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </>
  );
}

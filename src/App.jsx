import React, { useState } from 'react'
import Navbar from './components/Navbar'
import HeroSections from './sections/HeroSections'
import About from './sections/About'
import Stats from './sections/Stats'
import Dishes from './sections/Dishes'
import Features from './sections/Features'
import BookingProcess from './sections/BookingProcess'
import Timing from './sections/Timing'
import TestimonialSection from './sections/TestimonialSection'
import FAQs from './sections/FAQs'
import CTA from './sections/CTA'
import Footer from './components/Footer'
import BookingModal from './components/BookingModal'

const App = () => {
  const [isBookingModalOpen, setIsBookingModalOpen] = useState(false);

  const handleOpenBooking = () => {
    setIsBookingModalOpen(true);
  };

  const handleCloseBooking = () => {
    setIsBookingModalOpen(false);
  };

  return (
    <>
      <Navbar onBookTable={handleOpenBooking} />
      <HeroSections onBookTable={handleOpenBooking} />
      <About />
      <Stats />
      <Dishes />
      <Features />
      <BookingProcess onBookTable={handleOpenBooking} />
      <Timing onBookTable={handleOpenBooking} />
      <TestimonialSection />
      <FAQs />
      <CTA onBookTable={handleOpenBooking} />
      <Footer />

      {/* Interactive Spring Boot-connected Reservation Modal */}
      <BookingModal 
        isOpen={isBookingModalOpen} 
        onClose={handleCloseBooking} 
      />
    </>
  )
}

export default App

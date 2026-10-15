// API service for Restro Java Spring Boot Backend (http://localhost:8081/api)
import { dishes as defaultDishes, timingData as defaultTimings, testimonials as defaultTestimonials, faqs as defaultFaqs } from '../data/data';

const API_BASE_URL = 'http://localhost:8081/api';

/**
 * Fetch all available dishes
 */
export async function fetchDishes() {
  try {
    const response = await fetch(`${API_BASE_URL}/dishes`);
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    const json = await response.json();
    return json.data && json.data.length > 0 ? json.data : defaultDishes;
  } catch (err) {
    console.warn('Backend unavailable, using local dishes data:', err.message);
    return defaultDishes;
  }
}

/**
 * Fetch restaurant operating hours / timings
 */
export async function fetchTimings() {
  try {
    const response = await fetch(`${API_BASE_URL}/timings`);
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    const json = await response.json();
    return json.data && json.data.length > 0 ? json.data : defaultTimings;
  } catch (err) {
    console.warn('Backend unavailable, using local timings data:', err.message);
    return defaultTimings;
  }
}

/**
 * Fetch customer testimonials
 */
export async function fetchTestimonials() {
  try {
    const response = await fetch(`${API_BASE_URL}/testimonials`);
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    const json = await response.json();
    return json.data && json.data.length > 0 ? json.data : defaultTestimonials;
  } catch (err) {
    console.warn('Backend unavailable, using local testimonials data:', err.message);
    return defaultTestimonials;
  }
}

/**
 * Fetch FAQs
 */
export async function fetchFaqs() {
  try {
    const response = await fetch(`${API_BASE_URL}/faqs`);
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    const json = await response.json();
    return json.data && json.data.length > 0 ? json.data : defaultFaqs;
  } catch (err) {
    console.warn('Backend unavailable, using local FAQs data:', err.message);
    return defaultFaqs;
  }
}

/**
 * Submit table reservation
 */
export async function createReservation(reservationData) {
  const response = await fetch(`${API_BASE_URL}/reservations`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(reservationData),
  });
  
  const json = await response.json();
  if (!response.ok) {
    throw new Error(json.message || 'Failed to submit reservation');
  }
  return json;
}

/**
 * Submit contact message
 */
export async function sendContactMessage(contactData) {
  const response = await fetch(`${API_BASE_URL}/contact`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(contactData),
  });

  const json = await response.json();
  if (!response.ok) {
    throw new Error(json.message || 'Failed to send message');
  }
  return json;
}

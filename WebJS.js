const themeToggle = document.getElementById('theme-toggle');
if (themeToggle) {
    themeToggle.addEventListener('click', function() {
        document.body.classList.toggle('dark-theme');
        this.textContent = document.body.classList.contains('dark-theme') ? 'Светлая тема' : 'Темная тема';
    });
}

let currentSlide = 0;
const slides = document.querySelectorAll('.slide');

function showSlide(n) {
    slides.forEach(slide => slide.classList.remove('active'));
    currentSlide = n;
    if (currentSlide >= slides.length) currentSlide = 0;
    if (currentSlide < 0) currentSlide = slides.length - 1;
    slides[currentSlide].classList.add('active');
}

const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');

if (prevBtn) {
    prevBtn.addEventListener('click', () => showSlide(currentSlide - 1));
}

if (nextBtn) {
    nextBtn.addEventListener('click', () => showSlide(currentSlide + 1));
}

if (slides.length > 0) {
    setInterval(() => showSlide(currentSlide + 1), 5000);
}

function updateTimer() {
    const targetDate = new Date('2025-10-04T13:50:00');
    const now = new Date();
    const diff = targetDate - now;
    
    if (diff <= 0) {
        document.getElementById('timer').textContent = 'Пара уже началась!';
        return;
    }
    
    const days = Math.floor(diff / (1000 * 60 * 60 * 24));
    const hours = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
    
    document.getElementById('timer').textContent = 
        `${days}д:${hours.toString().padStart(2, '0')}ч:${minutes.toString().padStart(2, '0')}м`;
}

setInterval(updateTimer, 1000);
updateTimer();

const news = [
    'Telegram представил еще варианты, как влить в них бабки',
    'WhatsApp так и не смог улучшить безопасность',
    'Max разрешил звонить фсб',
    'ВКонтакте так ничего и не добавил',
    'Одноклассники запустили видеочат для бабушек'
];

let newsIndex = 0;
const newsContainer = document.getElementById('news-container');
const loadNewsBtn = document.getElementById('load-news');

function addNews() {
    if (newsIndex < news.length) {
        const newsItem = document.createElement('div');
        newsItem.className = 'news-item';
        newsItem.textContent = news[newsIndex];
        newsContainer.appendChild(newsItem);
        newsIndex++;
    } else {
        loadNewsBtn.disabled = true;
        loadNewsBtn.textContent = 'Больше новостей ты не заслужил';
    }
}

if (loadNewsBtn) {
    loadNewsBtn.addEventListener('click', addNews);
}

if (newsContainer) {
    addNews();
}

const form = document.getElementById('feedback-form');
if (form) {
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const message = document.getElementById('message').value;
        let valid = true;
        
        document.querySelectorAll('.error').forEach(el => el.textContent = '');
        
        if (name.length < 2) {
            document.getElementById('name-error').textContent = 'Имя слишком короткое';
            valid = false;
        }
        
        if (!email.includes('@')) {
            document.getElementById('email-error').textContent = 'Научись вводить почту плиз';
            valid = false;
        }
        
        if (message.length < 10) {
            document.getElementById('message-error').textContent = 'Придумай чет подлинее';
            valid = false;
        }
        
        if (valid) {
            alert('Отправил, не за что)');
            form.reset();
        }
    });
}
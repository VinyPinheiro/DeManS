class apache {

  package { "apache2":
    ensure  => present,
    require => Class["system-update"],
  }

  file {"/var/www/html/html":
  	ensure => "link",
  	target => "/vagrant/www",
  	require => Package["apache2"],
  	notify => Service["apache2"],
  }

  service { "apache2":
    ensure  => "running",
   require => Package["apache2"],
  }

}
